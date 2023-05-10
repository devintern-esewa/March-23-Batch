package com.airline.airlineticketing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserFallbackController {

    @GetMapping("/userServiceFallback")
    public String userServiceFallback(){
        return "User service is currently unavailable";
    }
}
