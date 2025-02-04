package com.myprojects.LMS.jwt;

import com.myprojects.LMS.Services.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.myprojects.LMS.jwt.*;
@RestController
public class JwtAuthenticationController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    private final JwtTokenService tokenService;
    Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationController(JwtTokenService tokenService,
                                       AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtTokenResponse> generateToken(
            @RequestBody JwtTokenRequest jwtTokenRequest) {
        logger.info("the username:"+customUserDetailsService.loadUserByUsername(jwtTokenRequest.username()));
        logger.error("the request is:"+jwtTokenRequest);
        var authenticationToken = 
                new UsernamePasswordAuthenticationToken(
                        jwtTokenRequest.username(), 
                        jwtTokenRequest.password());
        
        var authentication = 
                authenticationManager.authenticate(authenticationToken);
        
        var token = tokenService.generateToken(authentication);
        
        return ResponseEntity.ok(new JwtTokenResponse(token));
    }
}


