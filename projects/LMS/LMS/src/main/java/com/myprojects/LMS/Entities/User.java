package com.myprojects.LMS.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private boolean enabled;
    //private String role;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> authorities = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_books",joinColumns = @JoinColumn(name = "users_id")
            ,inverseJoinColumns = @JoinColumn(name = "books_id"))
    private List<Books> books;
}
