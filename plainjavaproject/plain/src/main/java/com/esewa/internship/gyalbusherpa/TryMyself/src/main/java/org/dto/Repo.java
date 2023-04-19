package org.dto;

import org.model.User;

import java.util.List;

public interface Repo {

    User findById(int userId);

    List<User> getAllUser();

    String getByUserNameById(int id);

    User deleteUser(int id);

    User createUser(User user);

    User updateUser(User user,int userId);

    void deleteUser(Integer userId);



}
