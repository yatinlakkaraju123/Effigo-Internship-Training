package com.myprojects.LMS.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "books")
@Data
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int totalQuantity;
    private int availableQuantity;
    @ManyToMany(mappedBy = "books")
    @JsonIgnore
    private List<User> users;
}
