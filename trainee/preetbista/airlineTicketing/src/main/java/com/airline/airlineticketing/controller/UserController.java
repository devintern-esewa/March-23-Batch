package com.airline.airlineticketing.controller;

import com.airline.airlineticketing.dto.UserDto;
import com.airline.airlineticketing.model.Product;
import com.airline.airlineticketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RestTemplate restTemplate;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/all")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "data";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/admin/add")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDTO) {
        UserDto newUser = userService.createUser(userDTO);
        String password = userDTO.getPassword();
        String encryptPassword = passwordEncoder.encode(password);
        userDTO.setPassword(encryptPassword);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        if (users != null) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        Optional<UserDto> userDtoOptional = userService.getUserById(userId);
        List<Product> productList = new ArrayList<>();
        Product product = this.restTemplate.getForObject("http://product-service/products/users/" + userId, Product.class);
        productList.add(product);

        if (userDtoOptional.isPresent()) {
            UserDto userDtoWithProductList = userDtoOptional.get();
            userDtoWithProductList.setProduct(productList);
            return ResponseEntity.ok(userDtoWithProductList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        boolean deleted = userService.deleteUser(userId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
