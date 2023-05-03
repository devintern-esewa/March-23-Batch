package com.example.mulltipledbconnectiontask.inventory.service;

import com.example.mulltipledbconnectiontask.inventory.dto.ProductResponseDto;
import com.example.mulltipledbconnectiontask.inventory.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getAllProducts(int startingId);

    ProductResponseDto getProductById(Long productId);

    List<Product> readProductDetailsFromFile(String filepath);

    List<Product> countSuccessFailureBeforeSavingProducts(List<Product> products, String filePath);

    void deleteProductById(Long productId);
}
