package org.example.service;

import org.example.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    boolean addNewProduct(Product product);

    boolean deleteById(int productId);

    boolean updateProduct(Product product);

    Product getById(int productId);

}
