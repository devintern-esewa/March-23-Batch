package com.airline.airlineticketing.service;

import com.airline.airlineticketing.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    boolean deleteUser(Long id);
}
