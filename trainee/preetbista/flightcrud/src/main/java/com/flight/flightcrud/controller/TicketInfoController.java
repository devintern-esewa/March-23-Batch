package com.flight.flightcrud.controller;

import com.flight.flightcrud.dto.TicketInfo;
import com.flight.flightcrud.service.TicketInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketInfoController {
    private final TicketInfoService ticketInfoService;

    public TicketInfoController(TicketInfoService ticketInfoService) {
        this.ticketInfoService = ticketInfoService;
    }

    @GetMapping("/bookFlightTicket/{id}")
    public TicketInfo findTicketInfoByPassengerId(@PathVariable Long id) {
        return ticketInfoService.findTicketInfoByPassengerId(id);
    }
}
