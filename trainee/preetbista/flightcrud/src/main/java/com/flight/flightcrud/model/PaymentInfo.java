package com.flight.flightcrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_info")
public class PaymentInfo {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "account_no")
    private String accountNo;

    private double amount;

    @Column(name = "card_type")
    private String cardType;

    @ManyToOne
    @JoinColumn(name = "passenger_info_id", referencedColumnName = "id")
    private PassengerInfo passengerInfo;
}
