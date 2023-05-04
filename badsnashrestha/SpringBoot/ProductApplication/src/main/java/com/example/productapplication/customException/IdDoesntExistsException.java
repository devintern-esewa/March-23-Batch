package com.example.productapplication.customException;

public class IdDoesntExistsException extends RuntimeException {
    public IdDoesntExistsException(String message) {
        super(message);
    }
}
