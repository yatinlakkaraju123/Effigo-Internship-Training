package com.myProjects.ManyToOne.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myProjects.ManyToOne.Entities.Authority;
import com.myProjects.ManyToOne.Entities.User;
import com.myProjects.ManyToOne.Repositories.AuthorityRepository;
import com.myProjects.ManyToOne.Repositories.UserRepository;

@Service
public class WebServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    Logger logger = LoggerFactory.getLogger(WebServices.class);
    public void RegisterUser(User user){
        
        try {
            user.setEnabled(true);
            String password = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(password);
            
        userRepository.save(user);
        Authority authority = new Authority();
        authority.setUsername(user.getUsername());
        authority.setAuthority("ROLE_USER");
        authorityRepository.save(authority);
        } catch (Exception e) {
                logger.error("Error", e);
        }
    }
    
}
