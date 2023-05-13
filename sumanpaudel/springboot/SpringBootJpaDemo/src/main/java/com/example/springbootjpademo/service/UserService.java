package com.example.springbootjpademo.service;

import com.example.springbootjpademo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface UserService {
    User saveUser(User user);

    List<User> getAllUser();

    void deleteUser(int id);

    Optional<User> getUserById(int id);

    User updateUserById(int id, User user);

    List<User> findUserByName(String name);

    List<User> findUserByAddress(String address);
}
