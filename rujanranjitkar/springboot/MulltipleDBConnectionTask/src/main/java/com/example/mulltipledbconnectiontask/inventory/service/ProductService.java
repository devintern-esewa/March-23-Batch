package com.example.mulltipledbconnectiontask.inventory.service;

import com.example.mulltipledbconnectiontask.inventory.dto.ProductResponseDto;
import com.example.mulltipledbconnectiontask.inventory.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    ProductResponseDto getProductById(Long productId);
    List<Product> readProductDetailsFromFile(String filepath);
    List<Product> saveProducts(List<Product> products);
    void deleteProductById(Long productId);
}
