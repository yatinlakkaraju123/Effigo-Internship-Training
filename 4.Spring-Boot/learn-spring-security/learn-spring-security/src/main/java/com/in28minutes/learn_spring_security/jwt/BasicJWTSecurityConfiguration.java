package com.in28minutes.learn_spring_security.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

//@Configuration
public class BasicJWTSecurityConfiguration {
    @Bean
    @Order(2147483642)
    SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth->
        {
            auth.anyRequest().authenticated();

        });
        http.sessionManagement(

                session ->
                        session.
                                sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
       // http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(header->
        {header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin);});

        return (SecurityFilterChain)http.build();
    }
//    @Bean
//    public UserDetailsService userDetailsService(){
//        var user = User.withUsername("yatin").password("{noop}123").roles("USER").build();
//        var admin = User.withUsername("admin").password("{noop}456").roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(user,admin);
//    }

    @Bean
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION).build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        var user = User.withUsername("yatin").password("123").passwordEncoder(str->
                        passwordEncoder().encode(str))
                .roles("USER").build();
        var admin = User.withUsername("admin").
                password("456").
                passwordEncoder(str-> passwordEncoder().
                        encode(str)).
                roles("ADMIN").
                build();
        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.createUser(user);
        jdbcUserDetailsManager.createUser(admin);
        return jdbcUserDetailsManager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
