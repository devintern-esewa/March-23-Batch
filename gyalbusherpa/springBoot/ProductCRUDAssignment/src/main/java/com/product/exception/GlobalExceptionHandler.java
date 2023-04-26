package com.product.exception;

import com.product.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> resourceNotFoundException(ResourceNotFoundException re) {
        ErrorDto dto = new ErrorDto();
        dto.setTimeStamp(new Date().toString());
        dto.setStatus("404");
        dto.setErrorMessage(re.getMessage());

        return new ResponseEntity<ErrorDto>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductAlreadyExistException.class)
    public ResponseEntity<ErrorDto> alreadyExistException(ProductAlreadyExistException re){
        ErrorDto dto = new ErrorDto();
        dto.setTimeStamp(new Date().toString());
        dto.setStatus("400");
        dto.setErrorMessage(re.getMessage());

        return new ResponseEntity<ErrorDto>(dto, HttpStatus.BAD_REQUEST);
    }

}
