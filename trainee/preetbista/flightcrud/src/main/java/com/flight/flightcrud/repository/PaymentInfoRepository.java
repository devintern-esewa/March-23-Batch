package com.flight.flightcrud.repository;

import com.flight.flightcrud.dto.TicketInfo;
import com.flight.flightcrud.model.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, String> {

    @Query(value = "SELECT new com.flight.flightcrud.dto.TicketInfo(p.id, p.name, p.email, pi.cardType)"
            + "FROM PassengerInfo p INNER JOIN PaymentInfo pi ON ?1 = pi.passengerInfo.id WHERE p.id = ?1")
    TicketInfo getTicketInfoByPassengerId(Long id);
}
