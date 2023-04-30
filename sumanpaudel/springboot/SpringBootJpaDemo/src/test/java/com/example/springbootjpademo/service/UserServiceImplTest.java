package com.example.springbootjpademo.service;

import com.example.springbootjpademo.entity.User;
import com.example.springbootjpademo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;
    List<User> userList = new ArrayList<>();
    private User user;

    @BeforeEach
    public void setUp() {
        //arrange
        user = new User();
        user.setId(1);
        user.setName("John");
        user.setAddress("Pulchowk");
    }

    @Test
    public void testSaveUser_TestingUserSaveMethod_True() {
        when(userRepository.save(user)).thenReturn(user);
        //act
        User savedUser = userService.saveUser(user);

        //assert
        assertEquals(1, savedUser.getId());
    }

    @Test
    public void testGetUserById_TestingFindUserByName_True() {
        String name = "Suman";
        //arrange
        User user1 = new User(2, "saurav", "bhaktapur");
        User user2 = new User(3, "shreejal", "Lalitpur");
        User user3 = new User(4, "santosh", "bhaktapur");

        userList.add(user);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        when(userRepository.getUserByName(name)).thenReturn(userList);
        //act
        List<User> userList1 = userService.findUserByName(name);

        //assert
        assertEquals(userList, userList1);
    }

    @Test
    public void testGetAllUser_TestingGetAllUser_True() {
        when(userRepository.findAll()).thenReturn(userList);
        List<User> userList1 = userService.getAllUser();
        assertEquals(userList1, userList);
    }
    @Test
    public void testUpdateUserById_TestingUpdateUserById_True(){
        // arrange
        User userToUpdate = new User(5, "Suman", "Kathmandu");
        when(userRepository.findById(5)).thenReturn(Optional.of(userToUpdate));

        User updatedUser = new User(5, "Suman Paudel", "Lalitpur");

        // act
        User result = userService.updateUserById(5, updatedUser);

        // assert
        assertEquals(updatedUser.getName(), result.getName());
        assertEquals(updatedUser.getAddress(), result.getAddress());
        verify(userRepository, times(1)).findById(5);
        verify(userRepository, times(1)).save(userToUpdate);
    }
    @Test
    public void testGetUserById_TestingFindUserByAddress_True() {
        String address = "bhaktapur";
        //arrange
        User user1 = new User(2, "saurav", "bhaktapur");
        User user2 = new User(3, "shreejal", "Lalitpur");
        User user3 = new User(4, "santosh", "bhaktapur");

        userList.add(user);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        when(userRepository.getUserByAddress(address)).thenReturn(userList);
        //act
        List<User> userList1 = userService.findUserByAddress(address);

        //assert
        assertEquals(userList, userList1);
    }
}
