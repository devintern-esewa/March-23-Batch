package com.airline.airlineticketing.service.impl;

import com.airline.airlineticketing.dto.UserDto;
import com.airline.airlineticketing.model.User;
import com.airline.airlineticketing.repository.UserRepository;
import com.airline.airlineticketing.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDTO) {
        User user = new User(userDTO.getUserName(),
                userDTO.getPassword(), userDTO.getMobileNumber(), userDTO.getRole());
        User savedUser = userRepository.save(user);
        return new UserDto(savedUser.getUserName(),
                savedUser.getPassword(), savedUser.getMobileNumber(), savedUser.getRole());
    }

    @Override
    @Transactional
    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserDto(user.getUserName(),
                        user.getPassword(),
                        user.getMobileNumber(),
                        user.getRole()));
    }

    @Override
    @Transactional
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return false;
    }
}
