package np.com.esewa.learn.springsecuritysample.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import np.com.esewa.learn.springsecuritysample.entity.User;
import np.com.esewa.learn.springsecuritysample.resources.dtos.UserDto;
import np.com.esewa.learn.springsecuritysample.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.regex.Pattern;

/**
 * @author SatyaRajAwasth1 on 4/30/2023
 * Controller for handling mappings relating User
 */

@Controller
public class UserController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }



    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

//    mapping for registeration form
    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

//    For taking user data from registration form
    @PostMapping("/register")
    public String registeration(
            @Valid @ModelAttribute("user")UserDto userDto,
            BindingResult bindingResult,
            Model model ) {
        String emailPattern = "^(.+)@(\\S+)$";
        User existingUser = Pattern.matches(emailPattern, userDto.getEmail()) ?
                userService.findUserByEmail(userDto.getEmail()) :
                userService.findUserByUsername(userDto.getUsername());

        if (existingUser != null){
            bindingResult.rejectValue("email","User already exists");
        }

        if (bindingResult.hasErrors()){
            model.addAttribute("user",userDto);
            return "/register";
        }

        userService.saveUser(userDto);

        return "redirect:/registration?success";
    }

}
