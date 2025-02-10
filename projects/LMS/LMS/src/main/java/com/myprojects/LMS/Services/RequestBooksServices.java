package com.myprojects.LMS.Services;

import com.myprojects.LMS.DTOs.RequestBooksDTO;
import com.myprojects.LMS.Entities.Books;
import com.myprojects.LMS.Entities.RequestBooks;
import com.myprojects.LMS.Entities.User;
import com.myprojects.LMS.Mappers.RequestBooksMapper;
import com.myprojects.LMS.Repositories.BooksRepository;
import com.myprojects.LMS.Repositories.RequestBooksRepository;
import com.myprojects.LMS.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestBooksServices {
    @Autowired
    private RequestBooksRepository requestBooksRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private RequestBooksMapper requestBooksMapper;
    public ResponseEntity<List<RequestBooksDTO>> retrieveAllRequestBooks(){
        try
        {
            List<RequestBooks> requestBooks = requestBooksRepository.findAll();
            List<RequestBooksDTO> requestBooksDTOS = requestBooks.stream().map(requestBook
                    ->requestBooksMapper.requestBooksToRequestBooksDTO(requestBook)).toList();
            return new ResponseEntity<>(requestBooksDTOS,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> createRequest(int uid,int bid){
        try
        {
            Optional<User> user = userRepository.findById(uid);
            Optional<Books> book = booksRepository.findById(bid);
            if(user.isEmpty() || book.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            else{
                RequestBooks requestBooks = new RequestBooks();
                requestBooks.setBook(book.get());
                requestBooks.setUser(user.get());
                requestBooksRepository.save(requestBooks);
                return new ResponseEntity<>("request made",HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<String> deleteRequests(int rid){
        try
        {
            Optional<RequestBooks> requestBooks = requestBooksRepository.findById(rid);
            if(requestBooks.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
                requestBooksRepository.delete(requestBooks.get());
                return new ResponseEntity<>("deleted request",HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
