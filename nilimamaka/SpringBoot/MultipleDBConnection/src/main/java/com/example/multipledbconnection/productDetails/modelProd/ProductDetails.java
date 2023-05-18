package com.example.multipledbconnection.productDetails.modelProd;



import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "productsDetails")
public class ProductDetails{
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)


    private Long productId;
    private String productName;
//    @Enumerated(EnumType.STRING)
//    private ProductStatus productStatus;
//    @Convert(converter = AesEncrypter.class)
//    private String code;
    private Integer quantity;
    private double price;


}
