package com.example.springbootcrud.exception;

public class IdDoesNotExistsException extends RuntimeException {
    public IdDoesNotExistsException(String str) {
        super(str);
    }
}
