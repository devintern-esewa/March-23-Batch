package com.multipledatabase.inventorydb.controller;


import com.multipledatabase.inventorydb.dto.ProductDto;
import com.multipledatabase.inventorydb.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {


    @Autowired
    ProductServiceImpl productService;


    @GetMapping(value = "/products")
    public List<ProductDto> getAllProduct() {

        return productService.getAllProduct();
    }


    @GetMapping(value = "/product/{name}")
    public ProductDto getProduct(@PathVariable String name) {

        return productService.findByName(name);
    }

    @PostMapping(value = "/product")
    public boolean addProduct(@RequestBody ProductDto productDto) {

        return productService.addProduct(productDto);

    }
}
