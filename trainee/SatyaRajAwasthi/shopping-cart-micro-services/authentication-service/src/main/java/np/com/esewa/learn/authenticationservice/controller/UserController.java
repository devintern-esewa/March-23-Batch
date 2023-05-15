package np.com.esewa.learn.authenticationservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/15/2023
 * @project shopping-cart-micro-services
 * Controller for login and basic functionalities
 */

@Controller
@RequestMapping("/api/auth")
public class UserController {

//    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
