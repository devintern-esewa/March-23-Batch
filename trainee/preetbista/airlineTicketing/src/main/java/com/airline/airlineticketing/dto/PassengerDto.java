package com.airline.airlineticketing.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PassengerDto implements Serializable {


    private String firstName;

    private String lastName;

    private String email;

    private Long phoneNumber;

    public PassengerDto( String firstName, String email, String lastName, Long phoneNumber) {
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public PassengerDto() {
    }
}
