package com.example.multipledatabaseconnectiontask.inventorydb.controller;

import com.example.multipledatabaseconnectiontask.inventorydb.dto.ProductDto;
import com.example.multipledatabaseconnectiontask.inventorydb.model.Product;
import com.example.multipledatabaseconnectiontask.inventorydb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getall")
    public List<Product> productDto() {
    return productService.getAllProduct();
    }
}
