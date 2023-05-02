package com.example.multipledatabaseconnection.productDetails.model;

import com.example.multipledatabaseconnection.aop.AesEncrypter;
import com.example.multipledatabaseconnection.productDetails.enums.ProductStatus;
import com.example.multipledatabaseconnection.timeStamps.TimeStamp;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "products")
public class ProductDetails extends TimeStamp {
    @Id
    @SequenceGenerator(sequenceName = "product_sequence",
            name = "product_name",
            allocationSize = 1)

    @GeneratedValue(strategy = GenerationType.AUTO,
            generator = "product_name")

    private Long productId;
    private String productName;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    @Convert(converter = AesEncrypter.class)
    private String code;
    private Integer quantity;
    private double price;


}
