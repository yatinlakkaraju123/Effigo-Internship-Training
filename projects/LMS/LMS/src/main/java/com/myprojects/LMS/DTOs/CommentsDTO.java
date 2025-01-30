package com.myprojects.LMS.DTOs;

import com.myprojects.LMS.Entities.Books;
import com.myprojects.LMS.Entities.User;
import lombok.Data;

@Data
public class CommentsDTO {
    private int id;
    private int rating;
    private String comment;
    private User user;
    private Books book;
}
