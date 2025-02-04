package com.myprojects.LMS.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authorities")
@Data
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String authority;
    @Override
    public String getAuthority() {
        return authority;
    }
}
