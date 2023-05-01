package com.example.mulltipledbconnectiontask.inventory.dto;

import com.example.mulltipledbconnectiontask.inventory.enums.ProductStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private String productName;
    private String code;
    private ProductStatus productStatus;
    private Long quantity;
    private double price;
}
