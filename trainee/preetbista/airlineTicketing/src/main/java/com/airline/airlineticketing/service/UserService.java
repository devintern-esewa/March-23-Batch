package com.airline.airlineticketing.service;

import com.airline.airlineticketing.dto.UserDto;
import com.airline.airlineticketing.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto createUser(UserDto userDto);

    Optional<UserDto> findByUserName(UserDto userDto);
    Optional<UserDto> getUserById(Long id);
    List<UserDto> getAllUsers();
    boolean deleteUser(Long id);

    User findByUsername(String username);
}
