package com.flight.flightcrud.dto;

import com.flight.flightcrud.model.PassengerInfo;
import com.flight.flightcrud.model.PaymentInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightBookingAcknowledgment {
    private String status;
    private double totalFare;
    private String pnrNo;
    private PassengerInfo passengerInfo;
    private TicketInfo ticketInfo;
}
