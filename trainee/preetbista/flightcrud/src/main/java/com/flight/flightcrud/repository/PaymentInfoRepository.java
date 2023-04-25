package com.flight.flightcrud.repository;

import com.flight.flightcrud.dto.TicketInfo;
import com.flight.flightcrud.model.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, String> {

//    @Query(value = "SELECT p.id, p.name, p.email, pi.card_type "
//            + "FROM passenger_info p INNER JOIN payment_info pi ON ?1 = pi.passenger_info_id",
//            nativeQuery = true)
//    TicketInfo getTicketInfoByPassengerId(Long id);

    @Query(value = "SELECT new com.flight.flightcrud.dto.TicketInfo(p.id, p.name, p.email, pi.cardType)"
            + "FROM PassengerInfo p INNER JOIN PaymentInfo pi ON ?1 = pi.passengerInfo.id")
    TicketInfo getTicketInfoByPassengerId(Long id);
}
