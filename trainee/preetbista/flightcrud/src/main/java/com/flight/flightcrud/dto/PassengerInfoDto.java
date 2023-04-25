package com.flight.flightcrud.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class PassengerInfoDto implements Serializable {
    private String name;

    private String email;

    private String source;

    private String destination;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date travelDate;

    private String pickupTime;

    private String arrivalTime;

    private double fare;
}
