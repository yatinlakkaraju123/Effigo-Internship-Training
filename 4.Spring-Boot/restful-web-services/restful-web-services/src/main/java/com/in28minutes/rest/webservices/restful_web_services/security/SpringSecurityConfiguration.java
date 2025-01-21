package com.in28minutes.rest.webservices.restful_web_services.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {   //Step1: All requests should be authenticated
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );
        // Step 2: If a request is not authenticated generally web login page is shown but we are overriding that for http api authorization
        http.httpBasic(Customizer.withDefaults());
        // Step 3: disable CSRF Tokens
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

}
