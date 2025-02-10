package com.myprojects.LMS.DTOs;

import com.myprojects.LMS.Entities.Books;
import com.myprojects.LMS.Entities.User;
import lombok.Data;

@Data
public class RequestBooksDTO {
    private int id;
    private User requestedUser;
    private Books requestBook;
}
