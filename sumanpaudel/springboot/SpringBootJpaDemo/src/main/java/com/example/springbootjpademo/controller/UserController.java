package com.example.springbootjpademo.controller;

import com.example.springbootjpademo.entity.User;
import com.example.springbootjpademo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // create employee rest api
    @PostMapping(value = "adduser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    // displaying all users
    @GetMapping("/")
    public ResponseEntity<List<User>> getUser() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    // displaying users by given id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable int id) {
        userService.deleteUser(id);
        return "User Deleted Successfully !";
    }

    @PutMapping("/update/{id}")
    public String updateUserById(@PathVariable int id, @RequestBody User user) {
        userService.updateUserById(id, user);
        return "User Updated Successfully !";
    }

    @GetMapping("/search/{name}")
    public List<User> getUserByName(@PathVariable String name) {
        return userService.findUserByName(name);
    }

    @GetMapping("/searchbyaddress/{address}")
    public List<User> getUserByAddress(@PathVariable String address) {
        return userService.findUserByAddress(address);
    }
}
