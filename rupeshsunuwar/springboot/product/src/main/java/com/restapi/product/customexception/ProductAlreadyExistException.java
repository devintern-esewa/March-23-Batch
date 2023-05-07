package com.restapi.product.customexception;

public class ProductAlreadyExistException extends RuntimeException {

    public ProductAlreadyExistException(String str) {

        super(str);
    }
}
