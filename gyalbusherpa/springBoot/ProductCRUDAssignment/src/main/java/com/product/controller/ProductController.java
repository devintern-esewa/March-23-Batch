package com.product.controller;


import com.product.dto.ProductDto;
import com.product.payload.ApiResponse;
import com.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/don")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);


    // Add product
    @PostMapping("/product")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
        logger.info("Saving product..");
        return new ResponseEntity<ProductDto>(productService.saveProduct(productDto), HttpStatus.CREATED);
    }

    // Get All product
    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        logger.info("fetching all products..");
        return new ResponseEntity<List<ProductDto>>(productService.getAllProducts(), HttpStatus.OK);
    }

    // Get product by id
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") long id) {
        logger.info("Fetching product..");
        return new ResponseEntity<ProductDto>(productService.getProductById(id), HttpStatus.OK);
    }

    //update product
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") long id, @RequestBody ProductDto productDto){
        logger.info("Updating product..");
        return new ResponseEntity<ProductDto>(productService.updateProduct(id,productDto), HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") long id){
        logger.info("Deleting product..");
        productService.deleteProduct(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("deleted product successfully",true),HttpStatus.OK);
    }

}
