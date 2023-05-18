package com.airline.airlineticketing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> role;

    @Transient
    List<Product> product = new ArrayList<>();

    public User(String username, String password, Long mobileNumber, List<Role> role, List<Product> product) {
        this.userName = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.role = role;
        this.product=product;
    }

    public User(String username, String password, Long mobileNumber, List<Role> role) {
        this.userName = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.role = role;
    }
}
