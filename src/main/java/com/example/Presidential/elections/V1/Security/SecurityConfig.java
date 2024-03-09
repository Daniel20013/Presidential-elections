package com.example.Presidential.elections.V1.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/CSS/**", "/JS/**").permitAll()
                        .requestMatchers("/", "/registerPage", "/register", "/login", "/firstPage", "/userPage", "/home", "/registerCandidateTable", "/registerCandidate", "/candidates", "/candidate", "/voting", "/myCandudatePage").permitAll()
                        .anyRequest().authenticated())
                .formLogin((from) -> from
                        .loginPage("/")
                        .permitAll())
                .logout((logout) -> logout
                        .logoutSuccessUrl("/logout").permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
