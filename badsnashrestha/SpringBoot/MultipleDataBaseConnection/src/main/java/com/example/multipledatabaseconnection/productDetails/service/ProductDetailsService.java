package com.example.multipledatabaseconnection.productDetails.service;

import com.example.multipledatabaseconnection.productDetails.dto.ProductDetailsResponseDto;
import com.example.multipledatabaseconnection.productDetails.model.ProductDetails;
import java.io.IOException;
import java.util.List;

public interface ProductDetailsService {
    List<ProductDetailsResponseDto> getAllProductDetails(int startingPage);

    ProductDetailsResponseDto getProductDetailsById(Long productDetailsId);

    List<ProductDetails> readCsvInsertIntoProductDetails(String filePath) throws IOException;

    List<ProductDetails> processingProduct(List<ProductDetails> productDetails, String filePath);

    List<ProductDetails> addNewProduct(List<ProductDetails> productDetails);

    void deleteProductDetailsById(Long productDetailsId);


}
