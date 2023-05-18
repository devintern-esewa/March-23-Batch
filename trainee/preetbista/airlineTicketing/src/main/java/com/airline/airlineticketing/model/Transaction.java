package com.airline.airlineticketing.model;

import com.airline.airlineticketing.enums.TicketStatus;
import com.airline.airlineticketing.timestamp.TimeStamp;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Table(name = "transactions")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Transaction extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Ticket> ticket;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

}


