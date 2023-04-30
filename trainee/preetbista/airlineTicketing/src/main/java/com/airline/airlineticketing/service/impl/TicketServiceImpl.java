package com.airline.airlineticketing.service.impl;

import com.airline.airlineticketing.dto.TicketDto;
import com.airline.airlineticketing.model.Ticket;
import com.airline.airlineticketing.repository.TicketRepository;
import com.airline.airlineticketing.repository.UserRepository;
import com.airline.airlineticketing.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private UserRepository userRepository;

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional
    public TicketDto createTicket(TicketDto ticketDTO) {

        Ticket ticket = new Ticket(
                ticketDTO.getFlightNumber(),
                ticketDTO.getDepartureAirport(),
                ticketDTO.getArrivalAirport(),
                ticketDTO.getDepartureTime(),
                ticketDTO.getArrivalTime(),
                ticketDTO.getPrice()

        );

        Ticket savedTicket = ticketRepository.save(ticket);
        return new TicketDto(savedTicket.getFlightNumber(),
                savedTicket.getArrivalAirport(),
                savedTicket.getDepartureAirport(),
                savedTicket.getArrivalTime(),
                savedTicket.getDepartureTime(),
                savedTicket.getPrice());

    }

    @Override
    public List<TicketDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketDto> ticketDTOs = new ArrayList<>();
        for (Ticket ticket : tickets) {
            ticketDTOs.add(new TicketDto(
                    ticket.getFlightNumber(),
                    ticket.getDepartureAirport(),
                    ticket.getArrivalAirport(),
                    ticket.getDepartureTime(),
                    ticket.getArrivalTime(),
                    ticket.getPrice()
            ));
        }
        return ticketDTOs;
    }

    @Override
    public Optional<TicketDto> getTicketById(Long id) {
        return ticketRepository.findById(id)
                .map(ticket -> new TicketDto(ticket.getFlightNumber(),
                            ticket.getDepartureAirport(),
                            ticket.getArrivalAirport(),
                            ticket.getDepartureTime(),
                            ticket.getArrivalTime(),
                            ticket.getPrice()));
    }
}
