package com.pratice.product.customexception;

import org.springframework.stereotype.Component;


public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
//    private static final long serialVersionUID =1L;
//    private String errorCode;
//    private String errormessage;

}
