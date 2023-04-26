package com.flight.flightcrud.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="passenger_info")
public class PassengerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = "citizenship_number")
    private Long citizenshipNumber;

    private String email;

    private String source;

    private String destination;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "travel_date")
    private Date travelDate;

    @Column(name = "pickup_time")
    private String pickupTime;

    @Column(name = "arrival_time")
    private String arrivalTime;

    private double fare;
}
