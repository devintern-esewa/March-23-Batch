package com.example.springbootcrud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long productId;
    private String productName;
    private double price;
}
