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

    @GetMapping(value = "/product/{id}")
    public ProductDto getProduct(@PathVariable Integer id) {

        return productServiceImpl.getProduct(id);
    }


    @PostMapping(value = "/product")
    public ProductDto addProduct(@RequestBody ProductDto product) {

        return productServiceImpl.addProduct(product);
    }

    @PutMapping(value = "/product/{id}")
    public ProductDto updateProduct(@PathVariable Integer id, @RequestBody ProductDto product) {

        return productServiceImpl.updateProduct(id, product);
    }

    @DeleteMapping(value = "/product/{id}")
    public void deleteProduct(@PathVariable Integer id) {

        productServiceImpl.deleteProduct(id);
    }
}

