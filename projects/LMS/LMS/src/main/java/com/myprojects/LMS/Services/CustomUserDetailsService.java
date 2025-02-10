package com.myprojects.LMS.Services;

import com.myprojects.LMS.Entities.Authority;
import com.myprojects.LMS.Entities.User;
import com.myprojects.LMS.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new MyUserDetails( // Use your custom UserDetails class
                user.getId(),         // Pass the ID here
                user.getUsername(),
                user.getPassword(),
                mapAuthorities(user.getAuthorities()), // Correctly map authorities
                user.isEnabled()       // Use the correct field for enabled

        );
    }

    private List<GrantedAuthority> mapAuthorities(Collection<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());
    }
}