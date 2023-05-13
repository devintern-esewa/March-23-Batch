package com.example.multipledatabaseconnectiontask.inventorydb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;
    private String code;
    private double quantity;
    private double price;
}
