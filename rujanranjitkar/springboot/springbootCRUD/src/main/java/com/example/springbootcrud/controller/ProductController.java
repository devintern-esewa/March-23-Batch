package com.example.springbootcrud.controller;

import com.example.springbootcrud.dto.ProductDto;
import com.example.springbootcrud.model.Product;
import com.example.springbootcrud.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/addNewProduct")
    public void addNewProduct(@RequestBody ProductDto productDto) {
        productService.addNewProduct(productDto);
    }

    @GetMapping("/getProductById/{productId}")
    public Optional<Product> getProductById(@PathVariable("productId") Long productId) {
        return productService.getProductById(productId);
    }

    @PutMapping("/updateProduct")
    public void updateProduct(@RequestBody ProductDto productDto) {
        productService.updateProduct(productDto);
    }

    @DeleteMapping("/deleteProductById/{productId}")
    public void deleteProductById(@PathVariable("productId") Long productId) {
        productService.deleteProductById(productId);
    }
}
