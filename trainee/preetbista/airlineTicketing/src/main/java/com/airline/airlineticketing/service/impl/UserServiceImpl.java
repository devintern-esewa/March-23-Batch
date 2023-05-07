package com.airline.airlineticketing.service.impl;

import com.airline.airlineticketing.dto.UserDto;
import com.airline.airlineticketing.exception.UserNotFoundException;
import com.airline.airlineticketing.model.User;
import com.airline.airlineticketing.repository.UserRepository;
import com.airline.airlineticketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
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
    public Optional<UserDto> findByUserName(UserDto userDto) {
        User user = userRepository.findByUserName(userDto.getUserName());
        if (user == null) {
            throw new
                    UserNotFoundException("No user found with username : "
                    + userDto.getUserName());
        } else {
            UserDto foundUserDto = new UserDto();
            foundUserDto.setUserName(user.getUserName());
            return Optional.of(foundUserDto);
        }
    }

    @Override
    @Transactional
    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserDto(
                        user.getUserName(),
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

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

}
