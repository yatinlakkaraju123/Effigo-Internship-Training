package com.myprojects.LMS.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import com.myprojects.LMS.Services.CustomUserDetailsService;
import com.myprojects.LMS.Services.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {
    
    private final JwtEncoder jwtEncoder;

    private final CustomUserDetailsService customUserDetailsService; // Inject your UserDetailsService

    public JwtTokenService(JwtEncoder jwtEncoder, CustomUserDetailsService customUserDetailsService) {
        this.jwtEncoder = jwtEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }


    public String generateToken(Authentication authentication) {
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        int userId = myUserDetails.getId();
        var scope = authentication
                        .getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                        .issuer("self")
                        .issuedAt(Instant.now())
                        .expiresAt(Instant.now().plus(90, ChronoUnit.MINUTES))
                        .subject(authentication.getName())
                        .claim("scope", scope)
                .claim("userId",userId)
                        .build();

        return this.jwtEncoder
                .encode(JwtEncoderParameters.from(claims))
                .getTokenValue();
    }
}


