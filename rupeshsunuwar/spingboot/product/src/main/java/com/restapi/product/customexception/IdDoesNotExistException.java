package com.restapi.product.customexception;

public class IdDoesNotExistException extends RuntimeException {


    public IdDoesNotExistException(String str) {
        super(str);
    }
}
