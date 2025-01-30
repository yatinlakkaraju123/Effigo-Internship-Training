package com.myprojects.LMS.Services;

import com.myprojects.LMS.DTOs.CommentsDTO;
import com.myprojects.LMS.DTOs.UserDTO;
import com.myprojects.LMS.Entities.Books;
import com.myprojects.LMS.Entities.Comments;
import com.myprojects.LMS.Entities.User;
import com.myprojects.LMS.Mappers.CommentsMapper;
import com.myprojects.LMS.Repositories.BooksRepository;
import com.myprojects.LMS.Repositories.CommentsRepository;
import com.myprojects.LMS.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// This service class defines methods for retrieving all comments and inserting a new comment
public class CommentsService {
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private CommentsMapper commentsMapper;

    public ResponseEntity<List<CommentsDTO>> retrieveAllComments(){
        try {
            List<Comments> comments = commentsRepository.findAll();
            List<CommentsDTO> commentsDTOS = comments.stream().map(comment ->
                    commentsMapper.commentsToCommentsDTO(comment)).toList();

            return new ResponseEntity<>(commentsDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> insertComment(int uid,int bid,CommentsDTO commentsDTO){
        try
        {
            Comments comments = commentsMapper.commentsDTOToComments(commentsDTO);
            Optional<User> getUser = userRepository.findById(uid);
            Optional<Books> getBook = booksRepository.findById(bid);
            if(getBook.isEmpty() || getUser.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                comments.setBook(getBook.get());
                comments.setUser(getUser.get());
                commentsRepository.save(comments);
                return new ResponseEntity<>("inserted comment",HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
