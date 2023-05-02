package com.example.mulltipledbconnectiontask.inventory.model;

import com.example.mulltipledbconnectiontask.inventory.aop.AesEncryptor;
import com.example.mulltipledbconnectiontask.inventory.enums.ProductStatus;
import com.example.mulltipledbconnectiontask.timeStamp.TimeStamp;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product extends TimeStamp {
    @Id
    @SequenceGenerator(sequenceName = "products_sequence",
            name = "products",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "products")
    private Long productId;

    private String productName;

    @Convert(converter = AesEncryptor.class)
    private String code;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    private Long quantity;

    private double price;
}
