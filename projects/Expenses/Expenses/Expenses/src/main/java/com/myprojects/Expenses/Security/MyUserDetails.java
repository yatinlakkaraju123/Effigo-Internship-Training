package com.myprojects.Expenses.Security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserDetails implements UserDetails {
    @Getter
    private final long id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    //private final boolean enabled;
    //private final boolean accountNonExpired;
    //private final boolean accountNonLocked;
    //private final boolean credentialsNonExpired;


    public MyUserDetails(long id, String username, String password, Collection<? extends GrantedAuthority> authorities

                         ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;

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


}
