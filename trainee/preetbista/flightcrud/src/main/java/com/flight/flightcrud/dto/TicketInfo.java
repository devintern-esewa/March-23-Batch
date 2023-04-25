package com.flight.flightcrud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketInfo implements Serializable {
    private String passengerId;
    private String name;
    private String email;
    private String cardType;
}
