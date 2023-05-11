package com.multipledatabase.inventorydb.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {

    private String name;
    private String code;
    private String status;
    private double qty;
    private double price;
}
