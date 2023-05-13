package com.example.productapplication.customException;

public class IdAlreadyExistsException extends RuntimeException {
    public IdAlreadyExistsException(String message) {
        super(message);
    }
}
