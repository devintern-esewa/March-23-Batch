package com.restapi.product.model;


import com.restapi.product.enums.ProductEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.TrueFalseType;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Product implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_Id")
    Integer productId;

    @Column(name = "product_Name")
    String productName;

    @Column(name = "product_Status")
    @Enumerated(EnumType.STRING)
    ProductEnum productStatus;

    @Column(name = "productPrice")
    Double productPrice;
    @Column(name = "productCategory")
    String productCategory;
    @Column(name = "productQuality")
    String productQuality;


}
