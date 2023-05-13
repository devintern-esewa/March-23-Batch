package com.example.productapplication.customException;

public class InvalidIdException extends RuntimeException {
    public InvalidIdException(String exception) {
        super(exception);
    }
}
