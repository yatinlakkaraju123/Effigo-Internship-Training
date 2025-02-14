package com.myprojects.Expenses.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "expenses")
@Data
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int price;
    private String description;
    private Date date;
    @ManyToOne
    @JsonIgnore
    private Users user;
    @ManyToOne
    private Category category;

}
