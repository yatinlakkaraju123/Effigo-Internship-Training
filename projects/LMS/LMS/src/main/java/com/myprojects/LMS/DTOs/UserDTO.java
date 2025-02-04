package com.myprojects.LMS.DTOs;

import com.myprojects.LMS.Entities.Books;
import lombok.Data;

import java.util.List;
@Data
public class UserDTO {
    private int id;
    private String username;
    private String password;

    //private String role;
    private List<Books> books;

}
