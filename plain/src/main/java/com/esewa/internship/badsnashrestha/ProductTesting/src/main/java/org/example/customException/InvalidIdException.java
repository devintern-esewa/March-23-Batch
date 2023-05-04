package org.example.customException;

public class InvalidIdException extends RuntimeException{
    //catches the string value
    public InvalidIdException(String str) {
        super(str);
    }
}
