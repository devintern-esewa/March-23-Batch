package com.airline.airlineticketing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mobile_number")
    private Long mobileNumber;

    @Column(name = "user_name")
    private String userName;

    @Column(nullable = false)
    private String password;

    private String role;

    public User(String username, String password, Long mobileNumber, String role) {
        this.userName = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.role = role;
    }
}