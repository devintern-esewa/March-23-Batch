package org.service;

import org.dto.Repo;
import org.model.User;

import java.util.List;

public class UserService {
    private List<User> userList;
    private User user;

    private Repo repo;

    public User createUser(User user) {
        return repo.createUser(user);
    }

    public List<User> getAllUser() {
        return repo.getAllUser();
    }

    public String getByUserNameById(int id) {

        if(id==0){
            throw new RuntimeException("You are don");
        }

        if(id==5){
            return repo.getByUserNameById(5);
        }
        return null;
    }


    public User deleteUser(int id) {
        return repo.deleteUser(id);
    }

    User findById(int userId){
        return null;
    }

    public User updateUser(User user, int userId) {

        User updateUser = repo.findById(userId);

        updateUser.setId(user.getId());
        updateUser.setName(user.getName());
        return repo.updateUser(updateUser,userId);
    }
}
