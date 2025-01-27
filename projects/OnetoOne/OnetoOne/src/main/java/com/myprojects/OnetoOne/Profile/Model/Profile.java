package com.myprojects.OnetoOne.Profile.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myprojects.OnetoOne.User.Model.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;

    @OneToOne(mappedBy = "profile")
    @JsonIgnore
    private User user;
}
