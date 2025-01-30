package com.myprojects.LMS.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "comments")
@Data
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int rating;
    private String comment;
    @ManyToOne
    private User user;
    @ManyToOne
    private Books book;
}
