package com.flight.flightcrud.service.impl;

import com.flight.flightcrud.dto.*;
import com.flight.flightcrud.exception.DuplicateBookingException;
import com.flight.flightcrud.exception.NoRecordsFoundException;
import com.flight.flightcrud.model.PassengerInfo;
import com.flight.flightcrud.model.PaymentInfo;
import com.flight.flightcrud.repository.PassengerInfoRepository;
import com.flight.flightcrud.repository.PaymentInfoRepository;
import com.flight.flightcrud.service.FlightBookingService;
import com.flight.flightcrud.utils.FlightBookingUtils;
import com.flight.flightcrud.utils.PaymentUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class FlightBookingServiceImpl implements FlightBookingService {
    private final PassengerInfoRepository passengerInfoRepository;
    private final PaymentInfoRepository paymentInfoRepository;

    public FlightBookingServiceImpl(PassengerInfoRepository passengerInfoRepository, PaymentInfoRepository paymentInfoRepository) {
        this.passengerInfoRepository = passengerInfoRepository;
        this.paymentInfoRepository = paymentInfoRepository;
    }

    private PassengerInfo convertPassegerDtoToPassengerInfo(PassengerInfoDto passengerInfoDto) {
        PassengerInfo info = new PassengerInfo();
        info.setName(passengerInfoDto.getName());
        info.setCitizenshipNumber(passengerInfoDto.getCitizenshipNumber());
        info.setEmail(passengerInfoDto.getEmail());
        info.setSource(passengerInfoDto.getSource());
        info.setDestination(passengerInfoDto.getDestination());
        info.setArrivalTime(passengerInfoDto.getArrivalTime());
        info.setFare(passengerInfoDto.getFare());
        info.setPickupTime(passengerInfoDto.getPickupTime());
        info.setTravelDate(passengerInfoDto.getTravelDate());
        return info;
    }

    @Transactional
    public FlightBookingAcknowledgment bookFlightTicket(FlightBookingRequest flightBookingRequest) {
        /*PassengerInfoDto passengerInfoDto = flightBookingRequest.getPassengerInfo();

        PassengerInfo passengerInfo = convertPassegerDtoToPassengerInfo(passengerInfoDto);

        passengerInfo.setId(passengerInfoRepository.save(passengerInfo).getId());

        TicketInfo ticketInfo = paymentInfoRepository.getTicketInfoByPassengerId(passengerInfo.getId());

        Optional<PassengerInfo> existingBooking = passengerInfoRepository.findById(passengerInfo.getId());
        if (!existingBooking.isPresent()) {
            throw new NoRecordsFoundException("No records were found for  id : " + passengerInfo.getId());
        }

        if (!FlightBookingUtils.validateBooking(passengerInfo, existingBooking.get())) {

            PaymentInfoDto paymentInfoDto = flightBookingRequest.getPaymentInfo();
            PaymentInfo paymentInfo = convertToPaymentInfo(paymentInfoDto);
            paymentInfo.setPassengerInfo(passengerInfo);

            PaymentUtils.validateCreditLimit(paymentInfoDto.getAccountNo(),
                    passengerInfoDto.getFare());

            paymentInfo.setAmount(passengerInfoDto.getFare());
            paymentInfoRepository.save(paymentInfo);
            return new FlightBookingAcknowledgment("SUCCESS", passengerInfoDto.getFare(),
                    UUID.randomUUID().toString().split("-")[0],
                    passengerInfo, ticketInfo);
        } else {
            throw new DuplicateBookingException("Your ticket has already been booked for date " + passengerInfo.getTravelDate());
        }*/

        //Validate: TODO: Add basic validation
        Optional<PassengerInfo> passengerInfoByCitizenshipNumberAndAndTravelDate = passengerInfoRepository
                .findPassengerInfoByCitizenshipNumberAndAndTravelDate(flightBookingRequest.getPassengerInfo().getCitizenshipNumber(), flightBookingRequest.getPassengerInfo().getTravelDate());

        if (passengerInfoByCitizenshipNumberAndAndTravelDate.isPresent()) {
            throw new DuplicateBookingException("Your ticket has already been booked for date " + flightBookingRequest.getPassengerInfo().getTravelDate());
        }

        //Convert to Entity
        PassengerInfoDto passengerInfoDto = flightBookingRequest.getPassengerInfo();
        PassengerInfo passengerInfo = convertPassegerDtoToPassengerInfo(passengerInfoDto);
        PassengerInfo savedPassengerInfo = passengerInfoRepository.save(passengerInfo);

//        passengerInfo.setId(passengerInfoRepository.save(passengerInfo).getId());

        TicketInfo ticketInfo = paymentInfoRepository.getTicketInfoByPassengerId(passengerInfo.getId());

        Optional<PassengerInfo> existingBooking = passengerInfoRepository.findById(passengerInfo.getId());
        if (!existingBooking.isPresent()) {
            throw new NoRecordsFoundException("No records were found for  id : " + passengerInfo.getId());
        }

        if (!FlightBookingUtils.validateBooking(passengerInfo, existingBooking.get())) {

            PaymentInfoDto paymentInfoDto = flightBookingRequest.getPaymentInfo();
            PaymentInfo paymentInfo = convertToPaymentInfo(paymentInfoDto);
            paymentInfo.setPassengerInfo(passengerInfo);

            PaymentUtils.validateCreditLimit(paymentInfoDto.getAccountNo(), passengerInfoDto.getFare());

            paymentInfo.setAmount(passengerInfoDto.getFare());
            paymentInfoRepository.save(paymentInfo);
            return new FlightBookingAcknowledgment("SUCCESS", passengerInfoDto.getFare(), UUID.randomUUID().toString().split("-")[0], passengerInfo, ticketInfo);
        } else {
            throw new DuplicateBookingException("Your ticket has already been booked for date " + passengerInfo.getTravelDate());
        }
    }

    private PaymentInfo convertToPaymentInfo(PaymentInfoDto paymentInfoDto) {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setPassengerInfo(paymentInfo.getPassengerInfo());
        paymentInfo.setCardType(paymentInfoDto.getCardType());
        paymentInfo.setAmount(paymentInfoDto.getAmount());
        paymentInfo.setAccountNo(paymentInfoDto.getAccountNo());
        return paymentInfo;
    }
    /*@Override
    public List<PassengerInfo> getPassengerInfosByCitizenshipNumberAndTravelDate(Long citizenshipNumber, Date travelDate) {
        List<PassengerInfo> existingPassengerInfosWithCitizenNoAndTravelDate = passengerInfoRepository.findPassengerInfosByCitizenshipNumberAndTravelDate(citizenshipNumber, travelDate);;
        if (existingPassengerInfosWithCitizenNoAndTravelDate == null){
            return Collections.emptyList();
        }
        else {
            return existingPassengerInfosWithCitizenNoAndTravelDate;
        }
    }*/
}
