package com.example.multipledatabaseconnection.productDetails.dto;

import com.example.multipledatabaseconnection.productDetails.enums.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsRequestDto {
    private String productName;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    private String code;
    private Integer quantity;
    private double price;
}
