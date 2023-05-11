package com.multipledatabase.inventorydb.exception;

public class ProductAlreadyExistException extends RuntimeException{


    public ProductAlreadyExistException(String str){

        super(str);

    }
}
