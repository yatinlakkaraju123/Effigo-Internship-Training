package com.myprojects.LMS.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.awt.print.Book;
import java.time.LocalDate;

@Entity
@Table(name = "borrowing")
@Data
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private LocalDate dueDate;
    private int fine;

    private boolean status;
    @ManyToOne
    private User user;
    @ManyToOne
    private Books book;
}
