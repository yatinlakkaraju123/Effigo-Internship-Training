package com.myprojects.LMS.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "request_books")
public class RequestBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Books book;
}
