package com.multipledatabase.inventorydb.controller;


import com.multipledatabase.inventorydb.dto.ProductDto;
import com.multipledatabase.inventorydb.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {


    @Autowired
    ProductServiceImpl productService;


    @GetMapping(value="/products")
    public List<ProductDto> getAllProduct(){

        return productService.getAllProduct();
    }
}
