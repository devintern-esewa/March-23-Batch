package com.example.springbootcrud.exception;

public class IdAlreadyExistsException extends RuntimeException {
    public IdAlreadyExistsException(String str) {
        super(str);
    }
}
