package com.example.springbootcrud.service;


import com.example.springbootcrud.dto.ProductDto;
import com.example.springbootcrud.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    void addNewProduct(ProductDto productDto);

    Optional<Product> getProductById(Long productId);

    void updateProduct(ProductDto productDto);

    void deleteProductById(Long productId);
}
