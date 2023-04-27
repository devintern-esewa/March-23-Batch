package com.flight.flightcrud.exception;

public class DuplicateBookingException extends RuntimeException{
    public DuplicateBookingException(String msg){
        super(msg);
    }
}
