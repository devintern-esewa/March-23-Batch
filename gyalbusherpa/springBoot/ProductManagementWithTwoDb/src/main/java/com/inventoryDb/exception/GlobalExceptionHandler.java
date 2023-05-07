package com.inventoryDb.exception;

import com.inventoryDb.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> resourceNotFound(ResourceNotFoundException re) {
        ErrorDto dto = new ErrorDto();
        dto.setTimeStamp(new Date().toString());
        dto.setStatus("404");
        dto.setErrorMessage(re.getMessage());

        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

}
