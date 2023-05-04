package com.flight.flightcrud.service;

import com.flight.flightcrud.dto.FlightBookingAcknowledgment;
import com.flight.flightcrud.dto.FlightBookingRequest;
import com.flight.flightcrud.model.PassengerInfo;

import java.util.Date;
import java.util.List;

public interface FlightBookingService {
    FlightBookingAcknowledgment bookFlightTicket(FlightBookingRequest request);
   /* List<PassengerInfo> getPassengerInfosByCitizenshipNumberAndTravelDate(Long citizenshipNumber, Date travelDate);*/
}
