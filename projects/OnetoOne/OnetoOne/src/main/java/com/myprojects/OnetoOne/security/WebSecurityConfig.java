package com.myprojects.OnetoOne.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    @Order(2147483642)
    SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth->
        {
            auth.requestMatchers(
                    "/login").permitAll().anyRequest().authenticated();

        });
//        http.sessionManagement(
//
//                session ->
//                        session.
//                                sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        );
         http.formLogin(form -> form.loginPage("/login")
                .defaultSuccessUrl("/", true) // Redirect to root URL after successful login
                .permitAll());
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(header->
        {header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin);});
//        http.oauth2ResourceServer((oauth2) -> oauth2
//                .jwt(Customizer.withDefaults()
//                ));

        return http.build();
    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("yatin")
//                        .password("123")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
}
