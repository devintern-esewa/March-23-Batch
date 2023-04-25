package com.example.productapplication.controller;

import com.example.productapplication.dto.ProductDto;
import com.example.productapplication.model.Product;
import com.example.productapplication.service.ProductService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class ProductController {
    public final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {

        this.productService = productService;
    }


    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    @PostMapping("/addNewProduct")
    public void addNewProduct(@RequestBody ProductDto productDto) {
        productService.addNewProduct(productDto);
    }

    @PostMapping("/addNewProductList")
    public void addNewProductList(@RequestBody List<ProductDto> productDto) {

        productService.addNewProductList(productDto);
    }

    @GetMapping("/getAllProduct/{productId}")
    public Optional<Product> getProductById(@PathVariable("productId") Integer productId) {
        return productService.getProductById(productId);
    }

    @DeleteMapping("/deleteProductById/{productId}")
    public void deleteProductById(@PathVariable("productId") Integer productId) {
        productService.deleteProductById(productId);
    }

    @PutMapping("/updateProduct")
    public void updateProduct(@RequestBody ProductDto productDto) {
        productService.updateProduct(productDto);
    }

}
