package com.example.springbootjpademo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LombookLoginController {
    @RequestMapping("/lombook")
    public String index(){
        log.info("this is logger");
        log.trace("This is trace message");
        log.debug("This is debug message");
        log.error("This is error message");
        return "Logger run successfully";
    }
}
