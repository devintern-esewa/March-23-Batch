package com.flight.flightcrud.utils;

import com.flight.flightcrud.model.PassengerInfo;

public class FlightBookingUtils {
    public static boolean validateBooking(PassengerInfo passengerInfo, PassengerInfo existingBooking ) {
        //Check via query i.e findByCitizenshipNUmberAndTravelDate

        if (passengerInfo.getCitizenshipNumber().equals(existingBooking.getCitizenshipNumber())
                && passengerInfo.getTravelDate().equals(existingBooking.getTravelDate())) {
            // If the citizenship number and travel date match an existing booking, the new booking is invalid
            return false;
        } else if (!passengerInfo.getTravelDate().equals(existingBooking.getTravelDate())) {
            // If the travel date is different from the existing booking, the new booking is valid
            return true;
        } else {
            // If the citizenship number is different from the existing booking, but the travel date is same,
            // the new booking is valid
            return true;
        }
    }
}
