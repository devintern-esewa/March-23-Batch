package com.controller;

import com.api.UserRegisterDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegistrationController {

    @RequestMapping("/register")
    public String showRegistrationPage(@ModelAttribute("register") UserRegisterDTO userRegisterDTO) {
        return "user-registration-page";
    }

    @RequestMapping("/registration-success")
    public String showRegistrationSuccess(@Valid @ModelAttribute("register") UserRegisterDTO userRegisterDTO,
                                          BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("error occurred");
            List<ObjectError> allErrors = result.getAllErrors();

            for (ObjectError error : allErrors) {
                System.out.println(error);
            }
            return "user-registration-page";
        }
        return "registration-success";
    }
}
