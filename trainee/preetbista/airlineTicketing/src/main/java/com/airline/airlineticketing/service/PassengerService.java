package com.airline.airlineticketing.service;

import com.airline.airlineticketing.dto.PassengerDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PassengerService {
    PassengerDto createPassenger(PassengerDto passengerDto);
    Optional<PassengerDto> getPassengerById(Long id);
    List<PassengerDto> getAllPassengers();
    void deletePassenger(Long id);
}
