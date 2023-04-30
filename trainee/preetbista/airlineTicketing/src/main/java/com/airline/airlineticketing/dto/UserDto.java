package com.airline.airlineticketing.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDto implements Serializable {

    private Long mobileNumber;

    private String userName;

    private String password;

    private String role;

    public UserDto(String userName, String password, Long mobileNumber, String role) {
        this.userName = userName;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.role = role;
    }

    public UserDto() {
    }
}
