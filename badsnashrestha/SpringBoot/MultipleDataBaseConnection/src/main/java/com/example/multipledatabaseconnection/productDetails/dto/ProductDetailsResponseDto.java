package com.example.multipledatabaseconnection.productDetails.dto;

import com.example.multipledatabaseconnection.productDetails.enums.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsResponseDto {
    private String productName;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    private String code;
    private Integer quantity;
    private double price;

}
