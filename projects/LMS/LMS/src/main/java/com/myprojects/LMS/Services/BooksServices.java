package com.myprojects.LMS.Services;

import com.myprojects.LMS.DTOs.BooksDTO;
import com.myprojects.LMS.DTOs.UserDTO;
import com.myprojects.LMS.Entities.Books;
import com.myprojects.LMS.Entities.User;
import com.myprojects.LMS.Mappers.BooksMapper;
import com.myprojects.LMS.Repositories.BooksRepository;
import com.myprojects.LMS.Repositories.BorrowRepository;
import com.myprojects.LMS.Repositories.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Service
public class BooksServices {
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private BooksMapper booksMapper;
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private CommentsRepository commentsRepository;
    public ResponseEntity<List<BooksDTO>> retrieveAllBooks() {
        try {
            List<Books> books = booksRepository.findAll();
            List<BooksDTO> bookDTOS = books.stream().map(book ->
                    booksMapper.booksToBooksDTO(book)).toList();

            return new ResponseEntity<>(bookDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> insertBooks(BooksDTO booksDTO) {
        try {
            Books book = booksMapper.booksDTOTobooks(booksDTO);
            booksRepository.save(book);
            return new ResponseEntity<>("book inserted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<BooksDTO> updateBook(int id, BooksDTO booksDTO) {
        try {
            Books books = booksMapper.booksDTOTobooks(booksDTO);
            Optional<Books> getBooks = booksRepository.findById(id);
            if (getBooks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                if(books.getName()!=null) getBooks.get().setName(books.getName());
                if(books.getAvailableQuantity()!=0)getBooks.get().
                        setAvailableQuantity(books.getAvailableQuantity());
                if(books.getTotalQuantity()!=0) getBooks.get().setTotalQuantity(books.getTotalQuantity());

                booksRepository.save(getBooks.get());
                BooksDTO booksDTO1= booksMapper.booksToBooksDTO(booksRepository.findById(id).get());
                return new ResponseEntity<>(booksDTO1, HttpStatus.OK);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<String> deleteBook(int id){
        try {
            Optional<Books> getBooks = booksRepository.findById(id);
            if (getBooks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                //List<Books> books = getUser.get().getBooks();
                List<User> users = getBooks.get().getUsers();
                for(User user:users){
                    user.getBooks().remove(getBooks.get());
                }
                borrowRepository.deleteAllByBook(id);
                commentsRepository.deleteAllByBook(id);
                booksRepository.delete(getBooks.get());
                return new ResponseEntity<>("deleted", HttpStatus.OK);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
