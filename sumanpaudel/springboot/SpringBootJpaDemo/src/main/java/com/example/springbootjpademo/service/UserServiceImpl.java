package com.example.springbootjpademo.service;

import com.example.springbootjpademo.repository.UserRepository;
import com.example.springbootjpademo.entity.User;
import com.example.springbootjpademo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }
    @Override
    public User updateUserById(int id, User user) {
        User user1 = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
        user1.setName(user.getName());
        user1.setAddress(user.getAddress());
        userRepository.save(user1);
        return user;
    }
    @Override
    public List<User> findUserByName(String name) {
        return userRepository.getUserByName(name);
    }
    @Override
    public List<User> findUserByAddress(String address) {
        return userRepository.getUserByAddress(address);
    }
}
