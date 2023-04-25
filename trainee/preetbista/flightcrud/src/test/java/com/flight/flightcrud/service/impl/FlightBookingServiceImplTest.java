package com.flight.flightcrud.service.impl;

import com.flight.flightcrud.dto.FlightBookingAcknowledgment;
import com.flight.flightcrud.dto.FlightBookingRequest;
import com.flight.flightcrud.model.PassengerInfo;
import com.flight.flightcrud.model.PaymentInfo;
import com.flight.flightcrud.repository.PassengerInfoRepository;
import com.flight.flightcrud.repository.PaymentInfoRepository;
import com.flight.flightcrud.service.FlightBookingService;
import com.flight.flightcrud.utils.PaymentUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ PaymentUtils.class })
public class FlightBookingServiceImplTest {
   /* @Mock
    private PassengerInfoRepository passengerInfoRepository;
    @Mock
    private PaymentInfoRepository paymentInfoRepository;

    @Test
    public void bookFlightTicket_SuccessfulBooking() throws Exception {
        // Arrange
        FlightBookingRequest bookingRequest = new FlightBookingRequest(
                new PassengerInfo("John Doe", "john.doe@example.com", 100.0),
                new PaymentInfo("John Doe", "1234567890123456", "01/22", "123", 100.0));

        PowerMockito.mockStatic(PaymentUtils.class);
        PowerMockito.doNothing().when(PaymentUtils.class, "validateCreditLimit",
                bookingRequest.getPaymentInfo().getAccountNo(), bookingRequest.getPassengerInfo().getFare());

        // Act
        FlightBookingAcknowledgment acknowledgment = bookingService.bookFlightTicket(bookingRequest);

        // Assert
        Assert.assertEquals("SUCCESS", acknowledgment.getStatus());
        Assert.assertEquals(bookingRequest.getPassengerInfo().getFare(), acknowledgment.getFare(), 0.0);
        Assert.assertNotNull(acknowledgment.getBookingId());
        Assert.assertEquals(bookingRequest.getPassengerInfo(), acknowledgment.getPassengerInfo());

        PowerMockito.verifyStatic(PaymentUtils.class, PowerMockito.times(1));
        PaymentUtils.validateCreditLimit(bookingRequest.getPaymentInfo().getAccountNo(),
                bookingRequest.getPassengerInfo().getFare());

        Mockito.verify(passengerInfoRepository, Mockito.times(1)).save(bookingRequest.getPassengerInfo());
        Mockito.verify(paymentInfoRepository, Mockito.times(1)).save(bookingRequest.getPaymentInfo());
    }*/

}
