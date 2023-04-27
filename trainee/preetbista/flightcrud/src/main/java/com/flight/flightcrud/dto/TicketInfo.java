package com.flight.flightcrud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class TicketInfo implements Serializable {

    private Long id;

    private String name;

    private String email;

    private String cardType;

    public TicketInfo(Long id, String name, String email, String cardType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cardType = cardType;
    }
}
