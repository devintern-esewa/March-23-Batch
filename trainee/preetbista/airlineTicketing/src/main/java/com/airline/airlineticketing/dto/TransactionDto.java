package com.airline.airlineticketing.dto;

import com.airline.airlineticketing.enums.TicketStatus;
import com.airline.airlineticketing.model.Ticket;
import com.airline.airlineticketing.model.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class TransactionDto implements Serializable {

    private List<Ticket> ticket;

    private User user;

    private TicketStatus ticketStatus;

    public TransactionDto(User user, List<Ticket> ticket, TicketStatus ticketStatus) {
        this.user = user;
        this.ticket=ticket;
        this.ticketStatus=ticketStatus;
    }

    public TransactionDto(){

    }
}
