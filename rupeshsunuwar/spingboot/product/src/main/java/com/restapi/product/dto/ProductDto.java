package com.restapi.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {


    Integer productId;
    String productName;
    Double productPrice;
    String productCategory;

    String productQuality;


}
