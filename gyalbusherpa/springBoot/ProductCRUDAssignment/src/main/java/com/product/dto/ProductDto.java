package com.product.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private String productName;
    private String productCode;
    private double quantity;
    private double price;
}
