package com.myprojects.JwtAuthentication.service;

import com.myprojects.JwtAuthentication.entity.UserInfo;
import com.myprojects.JwtAuthentication.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class UserInfoService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserInfoService.class); // Class-level logger

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

//    public UserInfoService(UserInfoRepository repository, PasswordEncoder encoder) {
//        this.repository = repository;
//        this.encoder = new BCryptPasswordEncoder();
//    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("loadUserByUsername() called for username: {}", username); // Use placeholders

        Optional<UserInfo> userDetail = repository.findByName(username);
        logger.debug("User found (Optional): {}", userDetail);

        if (userDetail.isEmpty()) {
            logger.debug("User not found: {}", username);
            throw new UsernameNotFoundException("User not found: " + username);
        }

        UserInfo userInfo = userDetail.get();
        logger.debug("User details: Email={}, Password={}, Roles={}", userInfo.getEmail(), userInfo.getPassword(), userInfo.getRoles());

        // ... rest of the method


        // Converting UserInfo to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public String addUser(UserInfo userInfo) {
        // Encode password before saving the user
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User Added Successfully";
    }
}
