package com.myprojects.Expenses.Security;

import com.myprojects.Expenses.Entities.Authorities;
import com.myprojects.Expenses.Entities.Users;
import com.myprojects.Expenses.Repositories.UserRepository;
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
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new MyUserDetails( // Use your custom UserDetails class
                user.getId(),         // Pass the ID here
                user.getUsername(),
                user.getPassword(),
                mapAuthorities(user.getAuthoritiesList()) // Correctly map authorities

        );
    }

    private List<GrantedAuthority> mapAuthorities(Collection<Authorities> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());
    }
}
