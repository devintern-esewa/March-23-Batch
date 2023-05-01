package com.airline.airlineticketing.dto;

import com.airline.airlineticketing.model.Ticket;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class TicketDto implements Serializable {

    private String flightNumber;

    private String departureAirport;

    private String arrivalAirport;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private double price;

    public TicketDto(String flightNumber, String departureAirport, String arrivalAirport, LocalDateTime departureTime, LocalDateTime arrivalTime, double price) {
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    public TicketDto() {
    }

    public static TicketDto of(Ticket ticket){
        return new TicketDto(
                ticket.getFlightNumber(),
                ticket.getDepartureAirport(),
                ticket.getArrivalAirport(),
                ticket.getDepartureTime(),
                ticket.getArrivalTime(),
                ticket.getPrice()
        );
    }
}
