package com.flight.flightcrud.exception;

public class InsufficientAmountException extends RuntimeException {
    public InsufficientAmountException(String msg) {
        super(msg);
    }
}
