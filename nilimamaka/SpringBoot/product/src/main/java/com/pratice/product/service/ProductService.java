package com.pratice.product.service;

import com.pratice.product.model.Product;

import javax.persistence.Id;
import java.util.List;

public interface ProductService {
    List<Product> getProduct();

    Product postProduct(Product product);

    Product putProduct(Product product, Long id);

    String deleteProduct(Long id);


}
