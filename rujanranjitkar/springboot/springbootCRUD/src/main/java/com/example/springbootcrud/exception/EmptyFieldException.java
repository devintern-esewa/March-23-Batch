package com.example.springbootcrud.exception;

public class EmptyFieldException extends RuntimeException{
    public EmptyFieldException(String str){
        super(str);
    }
}
