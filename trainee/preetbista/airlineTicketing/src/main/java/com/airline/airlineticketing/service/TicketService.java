package com.airline.airlineticketing.service;

import com.airline.airlineticketing.dto.TicketDto;
import com.airline.airlineticketing.model.Ticket;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface TicketService {
    TicketDto createTicket(TicketDto ticketDTO);
    List<TicketDto> getAllTickets();
    Optional<TicketDto> getTicketById(Long id);

}
