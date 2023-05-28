package com.don.solocanteenmanagementsystem.customer.config;

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

    private final CustomUserDetailServices customUserDetailServices;

    public SecurityConfig(CustomUserDetailServices customUserDetailServices) {
        this.customUserDetailServices = customUserDetailServices;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests()
                .mvcMatchers("/customers")
                .permitAll()
//                .mvcMatchers("/customers/{id:\\d+}")
                .anyRequest().authenticated()
                .and()
                .userDetailsService(customUserDetailServices)
                .formLogin().and().httpBasic().and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
