package utils;

import model.Product;

import java.util.List;

public class Util {
    public static double calculateDiscount(List<Product> products) {
        double sum = 0.0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        if(sum>10000)
            return 10/100*sum;
        else
            return 0.0;
    }

    public static double calculateAveragePrice(List<Product> products) {
        double sum = 0.0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return products.isEmpty() ? 0.0 : sum / products.size();
    }
}
