package com.airline.airlineticketing.dto;

import com.airline.airlineticketing.model.Role;
import com.airline.airlineticketing.model.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserDto implements Serializable {

    private Long mobileNumber;

    private String userName;

    private String password;

    private List<Role> role;

    public UserDto(String userName, String password, Long mobileNumber, List<Role> role) {
        this.userName = userName;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.role = role;
    }

    public static UserDto of(User user) {
        return new UserDto(user.getUserName(),
                user.getPassword(), user.getMobileNumber(), user.getRole());
    }

    public UserDto() {
    }
}
