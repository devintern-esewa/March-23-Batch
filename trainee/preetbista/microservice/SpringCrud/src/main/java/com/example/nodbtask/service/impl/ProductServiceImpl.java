package com.example.nodbtask.service.impl;

import com.example.nodbtask.model.Product;
import com.example.nodbtask.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    private static final List<Product> productList = new ArrayList<>();

    static {
        Product product1 = new Product(1L, "Laptop", "LP1", 120000L, 12L);
        Product product2 = new Product(2L, "Computer", "Cm4", 11000L, 8L);
        productList.add( product1);
        productList.add(product2);
    }

    public static List<Product> getAllProduct() {
        return new ArrayList<>(productList);
    }

    public Product findById(Long id) {
        return productList.get(1);
    }

    public Product addProduct(Product product) {
        // Get the next available ID by adding 1 to the current size of the productMap
        Long nextId = (long) (productList.size() + 1);
        product.setId(nextId);
        productList.add(product);
        return product;
    }

    public Product updateProduct(Long id, Product product) {
        product.setId(id);
        productList.add( product);
        return product;
    }

    public Product deleteProduct(int id) {
        return productList.remove(id);
    }
}