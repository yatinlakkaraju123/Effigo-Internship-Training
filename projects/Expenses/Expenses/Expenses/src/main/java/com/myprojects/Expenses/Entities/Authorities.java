package com.myprojects.Expenses.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "authorities")
@Data
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String authority;
    @ManyToMany(mappedBy = "authoritiesList")
    @JsonIgnore
    private List<Users> users = new ArrayList<>();

}
