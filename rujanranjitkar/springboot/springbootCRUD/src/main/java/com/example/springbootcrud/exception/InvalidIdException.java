package com.example.springbootcrud.exception;

public class InvalidIdException extends RuntimeException {
    public InvalidIdException(String str) {
        super(str);
    }
}
