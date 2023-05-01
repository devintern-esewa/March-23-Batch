package com.example.multipledatabaseconnection.productDetails.service;

import com.example.multipledatabaseconnection.productDetails.dto.ProductDetailsResponseDto;
import com.example.multipledatabaseconnection.productDetails.model.ProductDetails;

import java.io.IOException;
import java.util.List;

public interface ProductDetailsService {

    List<ProductDetails> readCsvInsertIntoProductDetails(String filePath) throws IOException;

    List<ProductDetails> addNewProduct(List<ProductDetails> productDetails);

    List<ProductDetails> processingProduct(List<ProductDetails> productDetails, String filePath);

    ProductDetailsResponseDto getProductDetailsById(Long productDetailsId);

    void deleteProductDetailsById(Long productDetailsId);
}
