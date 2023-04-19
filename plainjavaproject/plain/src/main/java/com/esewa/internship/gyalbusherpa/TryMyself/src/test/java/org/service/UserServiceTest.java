package org.service;

import org.dto.Repo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    Repo repo;

    @InjectMocks
    UserService service;

    @Test
    public void testCreateUser() {
        User user = new User(1, "DonIsMe");

        when(repo.createUser(user)).thenReturn(user);

        assertEquals("DonIsMe",service.createUser(user).getName());

        verify(repo).createUser(user);
    }

    @Test
    void testGetAllUser(){

        User u1 = new User(1,"never");
        User u2 = new User(1,"mind");

        List<User> user = new ArrayList<>();
        user.add(u1);
        user.add(u2);

        when(repo.getAllUser()).thenReturn(user);

        assertEquals(2,service.getAllUser().size());
        verify(repo,times(1)).getAllUser();

    }
    @Test
    public void testNullId(){
        assertThrows(RuntimeException.class,()->service.getByUserNameById(1));
    }
    @Test
    public void testGetUserById() {
        User user = new User(5,"don");

        when(repo.getByUserNameById(5)).thenReturn("don");

        assertEquals("don",service.getByUserNameById(user.getId()));
    }


    @Test
    public void testUpdateUser() {
        User user = new User(1, "John");

        when(repo.findById(1)).thenReturn(user);

        when(repo.updateUser(user, 1)).thenReturn(user);

        assertEquals(user, service.updateUser(user,1));

        verify(repo).updateUser(user, 1);
    }

    @Test
    public void testDelete(){

        service.deleteUser(1);

        verify(repo).deleteUser(anyInt());
    }

}