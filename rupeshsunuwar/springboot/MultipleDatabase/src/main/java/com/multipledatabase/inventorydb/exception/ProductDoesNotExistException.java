package com.multipledatabase.inventorydb.exception;

public class ProductDoesNotExistException extends RuntimeException{


    public ProductDoesNotExistException(String name){

        super(name);

    }
}
