package com.myprojects.LMS.Services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserDetails implements UserDetails {
    private final int id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;
    //private final boolean accountNonExpired;
    //private final boolean accountNonLocked;
    //private final boolean credentialsNonExpired;


    public MyUserDetails(int id, String username, String password, Collection<? extends GrantedAuthority> authorities,

                         boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;

    }

    public int getId() {
        return id;
    }

    // ... (UserDetails interface methods) ...
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

