package com.example.multipledatabaseconnection.productDetails.controller;

import com.example.multipledatabaseconnection.productDetails.dto.ProductDetailsResponseDto;
import com.example.multipledatabaseconnection.productDetails.model.ProductDetails;
import com.example.multipledatabaseconnection.productDetails.service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productDetails")
public class ProductDetailsController {
    @Autowired
    private ProductDetailsService productDetailsService;

    @GetMapping("/getAllProductDetailsUsingPagination/{startingPage}")
    public List<ProductDetailsResponseDto> getAllProductDetailsUsingPagination(@PathVariable("startingPage") int startingPage) {
        return productDetailsService.getAllProductDetails(startingPage);
    }

    @GetMapping("/getProductDetailsById/{productDetailsId}")
    public ProductDetailsResponseDto getProductDetailsById(@PathVariable("productDetailsId") Long productDetailsId) {
        return productDetailsService.getProductDetailsById(productDetailsId);
    }

    @DeleteMapping("/deleteProductById/{productDetailsId}")
    public void deleteProductDetailsById(@PathVariable("productDetailsId") Long productDetailsId) {
        productDetailsService.deleteProductDetailsById(productDetailsId);
    }


}
