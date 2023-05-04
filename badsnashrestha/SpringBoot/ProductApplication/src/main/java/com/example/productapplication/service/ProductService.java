package com.example.productapplication.service;

import com.example.productapplication.dto.ProductDto;
import com.example.productapplication.model.Product;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    public List<Product> getAllProducts();

    public void addNewProduct(ProductDto productDto);

    public Optional<Product> getProductById(Integer productId);

    public void deleteProductById(Integer productId);

    public void updateProduct(com.example.productapplication.dto.ProductDto productDto);
}
