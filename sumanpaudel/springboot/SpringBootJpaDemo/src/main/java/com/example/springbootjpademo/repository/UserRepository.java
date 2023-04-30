package com.example.springbootjpademo.repository;

import com.example.springbootjpademo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> getUserByName(String name);
    List<User> getUserByAddress(String address);
}
