package com.myprojects.Expenses.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "profile")
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private byte[] profilePic;
    private String email;
    private String username;
    private String phoneNumber;
    private String address;
    private boolean activatedProfile = false;
    @OneToOne(mappedBy = "profile")
    @JsonIgnore
    private Users user;

}
