package org.example.customException;

public class IdAlreadyExists extends RuntimeException{
    public IdAlreadyExists(String str) {
        super(str);
    }
}
