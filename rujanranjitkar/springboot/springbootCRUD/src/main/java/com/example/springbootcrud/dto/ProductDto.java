package com.example.springbootcrud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Long productId;
    private String productName;
    private double price;
}
