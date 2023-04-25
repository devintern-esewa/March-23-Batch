package com.flight.flightcrud.controller;

import com.flight.flightcrud.dto.FlightBookingAcknowledgment;
import com.flight.flightcrud.dto.FlightBookingRequest;
import com.flight.flightcrud.service.FlightBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequiredArgsConstructor
public class FlightBookingController {

    private final FlightBookingService flightBookingService;

    @PostMapping("/bookFlightTicket")
    public FlightBookingAcknowledgment bookFlightTicket(@RequestBody FlightBookingRequest flightBookingRequest) {
        return flightBookingService.bookFlightTicket(flightBookingRequest);
    }
}
