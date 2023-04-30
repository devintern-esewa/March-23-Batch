package com.example.multipledatabaseconnection.productDetails.model;

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

    @Column(name = "product_id")

    private Long productId;

    @Column(name = "product_name")
    private String productName;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    private Long quantity;

    @Column(name = "price")
    private double price;

}
