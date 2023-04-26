package com.flight.flightcrud.exception;

public class PassengerNotFoundException extends RuntimeException {
    public PassengerNotFoundException(String msg) {
        super(msg);
    }
}
