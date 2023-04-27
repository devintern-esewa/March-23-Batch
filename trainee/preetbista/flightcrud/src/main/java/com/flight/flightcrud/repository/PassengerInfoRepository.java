package com.flight.flightcrud.repository;

import com.flight.flightcrud.model.PassengerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PassengerInfoRepository extends JpaRepository<PassengerInfo, Long> {
    /*List<PassengerInfo> findPassengerInfosByCitizenshipNumberAndTravelDate(Long citizenshipNumber, Date travelDate);*/
    Optional<PassengerInfo> findPassengerInfoByCitizenshipNumberAndAndTravelDate(Long citizenshipNumber, Date travelDate);

}
