package com.example.nodbtask.service;

import com.example.nodbtask.model.Product;
import com.example.nodbtask.service.impl.ProductServiceImpl;

import java.util.List;

public interface ProductService {
    static List<Product> getAllProduct() {
        return ProductServiceImpl.getAllProduct();
    }

    Product findById(Long id);

    Product addProduct(Product product);

    Product updateProduct(Long id, Product product);

    Product deleteProduct(int id);
}
