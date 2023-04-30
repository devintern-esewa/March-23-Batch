package com.airline.airlineticketing.service;

import com.airline.airlineticketing.dto.TicketDto;
import com.airline.airlineticketing.model.Ticket;

import javax.transaction.Transactional;
import java.util.List;

public interface TicketService {
    TicketDto createTicket(TicketDto ticketDTO);
    List<TicketDto> getAllTickets();
    TicketDto getTicketById(Long id);

}
