package org.example.util;

import org.example.model.Product;

import java.util.List;

public class TotalUtil {
    public static float total(List<Product> products) {
        float sum = 0.0f;
        for (Product product : products
        ) {
            sum += product.getPrice();
        }
        return sum;
    }
}
