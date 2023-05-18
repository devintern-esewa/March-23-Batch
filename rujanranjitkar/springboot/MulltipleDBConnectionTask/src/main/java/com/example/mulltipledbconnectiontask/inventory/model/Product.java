package com.example.mulltipledbconnectiontask.inventory.model;

import com.example.mulltipledbconnectiontask.inventory.enums.ProductStatus;
import com.example.mulltipledbconnectiontask.timeStamp.TimeStamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    //    @Convert(converter = AesEncryptor.class) use this if you don't want to use aop for encryption and description
    private String code;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    private Long quantity;

    private double price;
}
