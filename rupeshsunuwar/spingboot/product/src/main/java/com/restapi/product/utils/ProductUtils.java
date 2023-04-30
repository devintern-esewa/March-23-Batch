package com.restapi.product.utils;

import com.restapi.product.dto.ProductDto;


import java.util.List;

public class ProductUtils {


    public static double calculateTotalPrice(List<ProductDto> products) {
        double totalPrice = 0;

        for (ProductDto product : products) {

            totalPrice += product.getProductPrice();

        }
        return totalPrice;
    }

    public static double calculateDiscount(double totalPrice) {
        double discountAmount = 0;

        if (totalPrice > 100000) {
            discountAmount = 10 / 100 * totalPrice;
        }

        return discountAmount;
    }
}
