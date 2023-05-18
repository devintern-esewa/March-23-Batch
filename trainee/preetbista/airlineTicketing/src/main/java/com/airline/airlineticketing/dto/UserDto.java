package com.airline.airlineticketing.dto;

import com.airline.airlineticketing.model.Product;
import com.airline.airlineticketing.model.Role;
import com.airline.airlineticketing.model.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto implements Serializable {

    private Long mobileNumber;

    private String userName;

    private String password;

    private List<Role> role;

    List<Product> product = new ArrayList<>();

    public UserDto(String userName, String password, Long mobileNumber, List<Role> role, List<Product> product) {
        this.userName = userName;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.role = role;
        this.product = product;
    }

    public static UserDto of(User user) {
        return new UserDto(user.getUserName(),
                user.getPassword(), user.getMobileNumber(), user.getRole(), user.getProduct());
    }

    public UserDto() {
    }
}
