package com.airline.airlineticketing.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException (String msg){
        super(msg);
    }
}
