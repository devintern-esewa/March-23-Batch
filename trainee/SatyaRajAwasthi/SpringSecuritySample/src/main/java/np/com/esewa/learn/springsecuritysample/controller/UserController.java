package np.com.esewa.learn.springsecuritysample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author SatyaRajAwasth1 on 4/30/2023
 * Controller for handling mappings relating User
 */

@Controller
public class UserController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
