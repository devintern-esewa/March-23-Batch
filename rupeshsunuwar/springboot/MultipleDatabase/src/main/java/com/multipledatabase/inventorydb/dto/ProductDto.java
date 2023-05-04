package com.multipledatabase.inventorydb.dto;

import com.multipledatabase.inventorydb.enums.ProductEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Data
@Getter
@Setter
public class ProductDto {

    private String name;
    private String code;
    private String status;
    private double qty;
    private double price;
}
