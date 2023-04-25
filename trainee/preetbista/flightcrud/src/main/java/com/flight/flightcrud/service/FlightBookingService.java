package com.flight.flightcrud.service;

import com.flight.flightcrud.dto.FlightBookingAcknowledgment;
import com.flight.flightcrud.dto.FlightBookingRequest;

public interface FlightBookingService {
    FlightBookingAcknowledgment bookFlightTicket(FlightBookingRequest request);
}
