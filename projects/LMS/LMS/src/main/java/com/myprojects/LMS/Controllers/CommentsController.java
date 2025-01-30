package com.myprojects.LMS.Controllers;

import com.myprojects.LMS.DTOs.CommentsDTO;
import com.myprojects.LMS.Entities.Comments;
import com.myprojects.LMS.Services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CommentsController {
    @Autowired
    private CommentsService commentsService;

    @GetMapping("/getComments")
    public ResponseEntity<List<CommentsDTO>> retrieveAllComments(){
        return commentsService.retrieveAllComments();
    }
    @PostMapping("/comment/users/{uid}/books/{bid}")
    public ResponseEntity<String> insertComment(@PathVariable int uid, @PathVariable int bid,
                                                @RequestBody CommentsDTO commentsDTO){
        return commentsService.insertComment(uid, bid, commentsDTO);
    }
}
