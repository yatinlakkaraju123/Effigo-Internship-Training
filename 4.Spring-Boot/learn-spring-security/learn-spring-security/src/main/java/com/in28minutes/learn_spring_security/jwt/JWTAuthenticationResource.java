package com.in28minutes.learn_spring_security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

//@RestController
public class JWTAuthenticationResource {
    private JwtEncoder jwtEncoder;

    public JWTAuthenticationResource(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(Authentication authentication)
    {
        return new JwtResponse(createToken(authentication));
    }

    private String createToken(Authentication authentication) {
         var claims = JwtClaimsSet.builder().
                issuer("self").
                issuedAt(Instant.now())
                .expiresAt(Instant.now().
                        plusSeconds(60*15)).
                subject(authentication.getName())
                .claim("scope",createScope(authentication)).build();
        JwtEncoderParameters parameters = JwtEncoderParameters.from(claims);
        return jwtEncoder.encode(parameters).getTokenValue();
    }

    private String createScope(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(a->
                        a.getAuthority()).collect(Collectors.joining(" "));
    }
}
record JwtResponse(String token){}
