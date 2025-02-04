package com.myprojects.LMS.Services;

import com.myprojects.LMS.Entities.Authority;
import com.myprojects.LMS.Entities.User;
import com.myprojects.LMS.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getAuthorities().stream()  // Convert authorities to roles
                        .map(Authority::getAuthority)
                        .toArray(String[]::new))
                .accountExpired(!user.isEnabled()) // Correct way to set enabled/disabled
                .credentialsExpired(!user.isEnabled())
                .disabled(!user.isEnabled())
                .accountLocked(!user.isEnabled())
                .build();
    }
}
