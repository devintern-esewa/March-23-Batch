package com.product.dto;

import lombok.Data;

@Data
public class ErrorDto {
    private String timeStamp;
    private String status;
    private String errorMessage;
}