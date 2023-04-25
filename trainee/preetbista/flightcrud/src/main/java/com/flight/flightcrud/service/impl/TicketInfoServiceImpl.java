package com.flight.flightcrud.service.impl;

import com.flight.flightcrud.dto.TicketInfo;
import com.flight.flightcrud.exception.PassengerNotFoundException;
import com.flight.flightcrud.model.PassengerInfo;
import com.flight.flightcrud.model.PaymentInfo;
import com.flight.flightcrud.repository.PassengerInfoRepository;
import com.flight.flightcrud.repository.PaymentInfoRepository;
import com.flight.flightcrud.service.TicketInfoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketInfoServiceImpl implements TicketInfoService {
    private final PassengerInfoRepository passengerInfoRepository;

    private final PaymentInfoRepository paymentInfoRepository;

    public TicketInfoServiceImpl(PassengerInfoRepository passengerInfoRepository,
                                 PaymentInfoRepository paymentInfoRepository) {
        this.passengerInfoRepository = passengerInfoRepository;
        this.paymentInfoRepository = paymentInfoRepository;
    }

    public TicketInfo findTicketInfoByPassengerId(Long id){
        Optional<PassengerInfo> passengerInfo = passengerInfoRepository.findById(id);
        if (!passengerInfo.isPresent()){
            throw new PassengerNotFoundException("Passenger not found for id : " + id);
        }
        TicketInfo ticketInfo = paymentInfoRepository.getTicketInfoByPassengerId(id);
        return ticketInfo;
    }

    private List<TicketInfo> convertToDetails(List<PaymentInfo> paymentInfos) {
        return paymentInfos.stream()
                .map(paymentInfo -> {
                    TicketInfo ticketInfo = new TicketInfo();
                    ticketInfo.setName(paymentInfo.getPassengerInfo().getName());
                    ticketInfo.setCardType(paymentInfo.getCardType());
                    ticketInfo.setEmail(paymentInfo.getPassengerInfo().getEmail());
                    return ticketInfo;
                })
                .collect(Collectors.toList());
    }
}
