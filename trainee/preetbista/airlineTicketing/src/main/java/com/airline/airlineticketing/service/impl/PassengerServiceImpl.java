package com.airline.airlineticketing.service.impl;

import com.airline.airlineticketing.dto.PassengerDto;
import com.airline.airlineticketing.model.Passenger;
import com.airline.airlineticketing.repository.PassengerRepository;
import com.airline.airlineticketing.service.PassengerService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;

    public PassengerServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    @Transactional
    public PassengerDto createPassenger(PassengerDto passengerDTO) {
        Passenger passenger = new Passenger(passengerDTO.getFirstName(),
                passengerDTO.getEmail(),
                passengerDTO.getLastName(),
                passengerDTO.getPhoneNumber());
        Passenger savedPassenger = passengerRepository.save(passenger);
        return new PassengerDto(
                savedPassenger.getFirstName(),
                savedPassenger.getEmail(),
                savedPassenger.getLastName(),
                savedPassenger.getPhoneNumber());
    }

    @Override
    public Optional<PassengerDto> getPassengerById(Long id) {
        return passengerRepository.findById(id)
                .map(passenger -> new PassengerDto(
                        passenger.getFirstName(),
                        passenger.getEmail(),
                        passenger.getLastName(),
                        passenger.getPhoneNumber()));
    }

    @Override
    public List<PassengerDto> getAllPassengers() {
        return passengerRepository.findAll().stream()
                .map(PassengerDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }
}
