package com.flight.flightcrud.service.impl;

import com.flight.flightcrud.dto.FlightBookingAcknowledgment;
import com.flight.flightcrud.dto.FlightBookingRequest;
import com.flight.flightcrud.dto.PassengerInfoDto;
import com.flight.flightcrud.dto.PaymentInfoDto;
import com.flight.flightcrud.model.PassengerInfo;
import com.flight.flightcrud.model.PaymentInfo;
import com.flight.flightcrud.repository.PassengerInfoRepository;
import com.flight.flightcrud.repository.PaymentInfoRepository;
import com.flight.flightcrud.service.FlightBookingService;
import com.flight.flightcrud.utils.PaymentUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class FlightBookingServiceImpl implements FlightBookingService {
    private final PassengerInfoRepository passengerInfoRepository;
    private final PaymentInfoRepository paymentInfoRepository;

    public FlightBookingServiceImpl(PassengerInfoRepository passengerInfoRepository,
                                    PaymentInfoRepository paymentInfoRepository) {
        this.passengerInfoRepository = passengerInfoRepository;
        this.paymentInfoRepository = paymentInfoRepository;
    }

    @Transactional
    public FlightBookingAcknowledgment bookFlightTicket(FlightBookingRequest flightBookingRequest) {
        PassengerInfoDto passengerInfoDto = flightBookingRequest.getPassengerInfo();

        PassengerInfo passengerInfo = convertPassegerDtoToPassengerInfo(passengerInfoDto);
        passengerInfoRepository.save(passengerInfo);

        PaymentInfoDto paymentInfoDto = flightBookingRequest.getPaymentInfo();
        PaymentInfo paymentInfo = convertToPassengerInfo(paymentInfoDto);

        PaymentUtils.validateCreditLimit(paymentInfoDto.getAccountNo(), passengerInfoDto.getFare());

        paymentInfo.setAmount(passengerInfoDto.getFare());
        paymentInfoRepository.save(paymentInfo);
        return new FlightBookingAcknowledgment("SUCCESS", passengerInfoDto.getFare(), UUID.randomUUID().toString().split("-")[0], passengerInfo);
    }

    private PaymentInfo convertToPassengerInfo(PaymentInfoDto paymentInfoDto) {
        PaymentInfo paymentInfos = new PaymentInfo();
        paymentInfos.setPassengerInfo(paymentInfoDto.getPassengerInfo());
        paymentInfos.setCardType(paymentInfoDto.getCardType());
        paymentInfos.setAmount(paymentInfoDto.getAmount());
        paymentInfos.setAccountNo(paymentInfoDto.getAccountNo());
        return paymentInfos;
    }

    private PassengerInfo convertPassegerDtoToPassengerInfo(PassengerInfoDto passengerInfoDto) {
        PassengerInfo info = new PassengerInfo();
        info.setName(passengerInfoDto.getName());
        info.setEmail(passengerInfoDto.getEmail());
        info.setSource(passengerInfoDto.getSource());
        info.setDestination(passengerInfoDto.getDestination());
        info.setArrivalTime(passengerInfoDto.getArrivalTime());
        info.setFare(passengerInfoDto.getFare());
        info.setPickupTime(passengerInfoDto.getPickupTime());
        info.setTravelDate(passengerInfoDto.getTravelDate());
        return info;
    }
}
