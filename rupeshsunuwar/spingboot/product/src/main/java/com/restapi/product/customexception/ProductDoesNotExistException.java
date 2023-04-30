package com.restapi.product.customexception;

public class ProductDoesNotExistException extends RuntimeException {


    public ProductDoesNotExistException(String str) {

        super(str);
    }
}
