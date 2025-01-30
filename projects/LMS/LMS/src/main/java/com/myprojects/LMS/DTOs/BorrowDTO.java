package com.myprojects.LMS.DTOs;

import com.myprojects.LMS.Entities.Books;
import com.myprojects.LMS.Entities.User;
import lombok.Data;

import java.time.LocalDate;
@Data
public class BorrowDTO {
    private int id;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private LocalDate dueDate;
    private int fine;
    private String status;
    private User user;
    private Books book;
}
