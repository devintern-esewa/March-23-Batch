package com.restapi.product.customexception;

public class IdAlreadyExistException extends RuntimeException {

    public IdAlreadyExistException(String str) {

        super(str);
    }
}
