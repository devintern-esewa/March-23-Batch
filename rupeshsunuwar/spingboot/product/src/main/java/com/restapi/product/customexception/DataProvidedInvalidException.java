package com.restapi.product.customexception;

public class DataProvidedInvalidException extends RuntimeException {


    public DataProvidedInvalidException(String str) {

        super(str);
    }
}
