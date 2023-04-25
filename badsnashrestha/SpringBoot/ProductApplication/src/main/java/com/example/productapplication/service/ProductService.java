package com.example.productapplication.service;

import com.example.productapplication.dto.ProductDto;
import com.example.productapplication.model.Product;
import com.example.productapplication.repo.ProductRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    public List<Product> getAllProducts();

    public void addNewProduct(ProductDto productDto);

    public void addNewProductList(List<ProductDto> productDto);

    public Optional<Product> getProductById(Integer productId);

    public void deleteProductById(Integer productId);

    public void updateProduct(ProductDto productDto);
}
