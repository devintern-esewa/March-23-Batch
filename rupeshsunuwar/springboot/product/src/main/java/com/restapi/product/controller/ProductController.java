package com.restapi.product.controller;


import com.restapi.product.dto.ProductDto;
import com.restapi.product.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/restapi")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;


    @GetMapping(value = "/products")
    public List<ProductDto> getAllProducts() {

        return productServiceImpl.getAllProducts();
    }

    @GetMapping(value = "/product/{productName}")
    public ProductDto getProduct(@PathVariable("productName") String productName) {

        return productServiceImpl.getProduct(productName);
    }


    @PostMapping(value = "/product")
    public ProductDto addProduct(@RequestBody ProductDto product) {

        return productServiceImpl.addProduct(product);
    }

    @PutMapping(value = "/product/{productName}")
    public ProductDto updateProduct(@PathVariable("productName") String productName, @RequestBody ProductDto product) {

        return productServiceImpl.updateProduct(productName, product);
    }

    @DeleteMapping(value = "/product/{productName}")
    public void deleteProduct(@PathVariable("productName" )String productName) {

        productServiceImpl.deleteProduct(productName);
    }
}

