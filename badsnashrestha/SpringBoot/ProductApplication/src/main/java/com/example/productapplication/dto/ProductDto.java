package com.example.productapplication.dto;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer productId;
    private String productName;
    private double price;
}
