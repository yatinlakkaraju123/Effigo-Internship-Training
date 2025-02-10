package com.myprojects.LMS.Services;

import com.myprojects.LMS.DTOs.BooksDTO;
import com.myprojects.LMS.DTOs.BorrowDTO;
import com.myprojects.LMS.Entities.Books;
import com.myprojects.LMS.Entities.Borrow;
import com.myprojects.LMS.Entities.User;
import com.myprojects.LMS.Mappers.BorrowMapper;
import com.myprojects.LMS.Repositories.BooksRepository;
import com.myprojects.LMS.Repositories.BorrowRepository;
import com.myprojects.LMS.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private BorrowMapper borrowMapper;

    public ResponseEntity<List<BorrowDTO>> retrieveAllBorrows() {
        try {
            List<Borrow> borrows = borrowRepository.findAll();
            List<BorrowDTO> borrowDTOS = borrows.stream().map(borrow ->
                    borrowMapper.borrowToBorrowDTO(borrow)).toList();

            return new ResponseEntity<>(borrowDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<String> borrow(int uid,int bid,BorrowDTO borrowDTO){
        try
        {
            Optional<User> user =  userRepository.findById(uid);
            Optional<Books> book = booksRepository.findById(bid);
            Borrow borrow = borrowMapper.borrowDTOToBorrow(borrowDTO);

            if(user.isEmpty() || book.isEmpty() ){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<Books> userBooks = user.get().getBooks();

            if(userBooks.contains(book.get())){
                    return ResponseEntity.badRequest().body("cannot borrow as book is already present");
                }
            else if(book.get().getAvailableQuantity()<=0){
                return ResponseEntity.badRequest().body("cannot borrow book as it is not available");
            }
            else{
                int availableQuantity = book.get().getAvailableQuantity();
                book.get().setAvailableQuantity(availableQuantity-1);
                borrow.setBook(book.get());
                borrow.setUser(user.get());
                book.get().getUsers().add(user.get());
                booksRepository.save(book.get());
                user.get().getBooks().add(book.get());
                userRepository.save(user.get());
                borrowRepository.save(borrow);
                return new ResponseEntity<>("created a borrow record",HttpStatus.ACCEPTED);
            }


        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<String> returned(int bid,BorrowDTO borrowDTO){
        try
        {
            Optional<Borrow> getBorrow = borrowRepository.findById(bid);
            Borrow borrow = borrowMapper.borrowDTOToBorrow(borrowDTO);
            if(getBorrow.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            else{
                int fine = 10;// fine is set as Rs 10 per day
                Books book = getBorrow.get().getBook();
                User user = getBorrow.get().getUser();
                book.getUsers().remove(user);
                int availableQuantity = book.getAvailableQuantity();
                book.setAvailableQuantity(availableQuantity+1);
                booksRepository.save(book);
                user.getBooks().remove(book);
                userRepository.save(user);
                getBorrow.get().setReturnDate(borrow.getReturnDate());
                getBorrow.get().setStatus(true);
                long days = ChronoUnit.DAYS.between(getBorrow.get().getDueDate()
                        ,getBorrow.get().getReturnDate());
                if(days>0) getBorrow.get().setFine((fine*(int)days));
                borrowRepository.save(getBorrow.get());
                return new ResponseEntity<>("created a return record",HttpStatus.ACCEPTED);
            }


        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
