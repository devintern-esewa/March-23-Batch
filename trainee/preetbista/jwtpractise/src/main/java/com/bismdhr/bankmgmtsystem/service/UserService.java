package com.bismdhr.bankmgmtsystem.service;

import com.bismdhr.bankmgmtsystem.dto.UserUpdateDto;
import com.bismdhr.bankmgmtsystem.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User findById(int id);

    User findByUsername(String username);

    User addUser(User user);

    User updateUser(UserUpdateDto user);

    String deleteUser(int id);
}
