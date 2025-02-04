package com.myProjects.ManyToOne.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
  prePostEnabled = true, 
  securedEnabled = true, 
  jsr250Enabled = true)
public class SecurityConfig {
    @Bean
    @Order(2147483642)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(
                    "/login", "/register").permitAll().anyRequest().authenticated();

        });
        // http.sessionManagement(
        //
        // session ->
        // session.
        // sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        // );
        http.formLogin(form -> form.loginPage("/login")
                .defaultSuccessUrl("/", true) // Redirect to root URL after successful login
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(header -> {
            header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin);
        });
        // http.oauth2ResourceServer((oauth2) -> oauth2
        // .jwt(Customizer.withDefaults()
        // ));

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
}
