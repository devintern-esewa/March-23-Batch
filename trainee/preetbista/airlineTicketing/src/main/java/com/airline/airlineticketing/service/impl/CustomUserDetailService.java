package com.airline.airlineticketing.service.impl;

import com.airline.airlineticketing.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(userService.findByUsername(username))
                .map(CustomUserDetails::new)
                .orElseThrow(() ->
                        new UsernameNotFoundException("No user found for username : " + username));
    }
}
