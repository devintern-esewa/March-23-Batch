package com.flight.flightcrud.repository;

import com.flight.flightcrud.model.PassengerInfo;
import com.flight.flightcrud.model.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, String> {

    //    @Query("select pi from PaymentInfo pi where pi.passengerInfo = ?1")
    List<PaymentInfo> findAllByPassengerInfo(PassengerInfo passengerInfo);
}
