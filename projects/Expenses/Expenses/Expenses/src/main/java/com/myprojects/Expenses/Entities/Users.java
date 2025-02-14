package com.myprojects.Expenses.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    @OneToOne
    private Profile profile;
    @ManyToMany
    @JoinTable(name = "user_authorities"
            ,joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authorities> authoritiesList = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Expenses> expenses = new ArrayList<>();

}
