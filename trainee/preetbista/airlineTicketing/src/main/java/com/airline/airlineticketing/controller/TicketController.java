package com.airline.airlineticketing.controller;

import com.airline.airlineticketing.dto.TicketDto;
import com.airline.airlineticketing.enums.TicketStatus;
import com.airline.airlineticketing.model.Ticket;
import com.airline.airlineticketing.model.Transaction;
import com.airline.airlineticketing.model.User;
import com.airline.airlineticketing.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDTO) {
        TicketDto newTicketDTO = ticketService.createTicket(ticketDTO);
        return ResponseEntity.ok(newTicketDTO);
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        List<TicketDto> tickets = ticketService.getAllTickets();
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        }
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable Long ticketId) {
        return ticketService.getTicketById(ticketId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    /*public void bookTicket(Long userId, List<String> ticketNumbers) {
         User user = userService.findById(userId);
         List<Ticket> tickets = ticketService.findByTicketNumbers(ticketNumbers);
        Transaction transaction = new Transaction();
        transaction.setTicket(tickets);
        transaction.setUser(user);
        transaction.setTicketStatus(TicketStatus.BOOKED);

    }*/

}
