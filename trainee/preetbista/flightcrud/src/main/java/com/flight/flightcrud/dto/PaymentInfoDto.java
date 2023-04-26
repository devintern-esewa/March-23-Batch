package com.flight.flightcrud.dto;

import com.flight.flightcrud.model.PassengerInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
public class PaymentInfoDto implements Serializable {
    private PassengerInfo passengerInfo;

    private String accountNo;

    private double amount;

    private String cardType;
}
