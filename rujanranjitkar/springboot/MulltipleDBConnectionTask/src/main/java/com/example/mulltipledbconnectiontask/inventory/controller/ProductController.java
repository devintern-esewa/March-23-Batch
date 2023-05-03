package com.example.mulltipledbconnectiontask.inventory.controller;

import com.example.mulltipledbconnectiontask.inventory.dto.ProductResponseDto;
import com.example.mulltipledbconnectiontask.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProducts/{startingPage}")
    public List<ProductResponseDto> getAllProducts(@PathVariable("startingPage") int startingPage) {
        return productService.getAllProducts(startingPage);
    }

    @GetMapping("/getProductById/{productId}")
    public ProductResponseDto getProductById(@PathVariable("productId") Long productId) {
        return productService.getProductById(productId);
    }

    @DeleteMapping("/deleteProductById/{productId}")
    public void deleteProductById(@PathVariable("productId") Long productId) {
        productService.deleteProductById(productId);
    }
}
