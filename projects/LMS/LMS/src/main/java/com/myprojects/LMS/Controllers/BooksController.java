package com.myprojects.LMS.Controllers;

import com.myprojects.LMS.DTOs.BooksDTO;
import com.myprojects.LMS.Entities.Books;
import com.myprojects.LMS.Services.BooksServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController {
    @Autowired
    private BooksServices booksServices;

    @GetMapping("/getBooks")
    public ResponseEntity<List<BooksDTO>> retrieveAllBooks(){
        return booksServices.retrieveAllBooks();
    }
    @PostMapping("/insertBooks")
    public ResponseEntity<String> insertBook(@RequestBody BooksDTO booksDTO){
        return booksServices.insertBooks(booksDTO);
    }
    @PutMapping("update/books/{id}")
    public ResponseEntity<BooksDTO> updateBook(@PathVariable int id,@RequestBody BooksDTO booksDTO){
        return booksServices.updateBook(id,booksDTO);
    }
    @DeleteMapping("delete/deleteBooks/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id){
        return booksServices.deleteBook(id);
    }
}
