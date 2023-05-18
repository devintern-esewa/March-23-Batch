package com.example.mulltipledbconnectiontask.inventory.dto;

import com.example.mulltipledbconnectiontask.inventory.enums.ProductStatus;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto implements Serializable {
    private String productName;
    private String code;
    private ProductStatus productStatus;
    private Long quantity;
    private double price;
}
