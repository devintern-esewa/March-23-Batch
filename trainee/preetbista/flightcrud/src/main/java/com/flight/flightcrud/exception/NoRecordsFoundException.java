package com.flight.flightcrud.exception;

public class NoRecordsFoundException extends RuntimeException{
    public NoRecordsFoundException(String msg){
        super(msg);
    }
}
