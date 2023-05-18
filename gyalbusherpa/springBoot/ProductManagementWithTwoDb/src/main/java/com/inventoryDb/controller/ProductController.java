package com.inventoryDb.controller;

import com.inventoryDb.dto.ProductDto;
import com.inventoryDb.payload.ApiResponse;
import com.inventoryDb.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{offset}/{pageSize}/{field}")
    public ResponseEntity<List<ProductDto>> getAllProductsByPagingAndSorting(@PathVariable("offset") int offSet,
                                                                             @PathVariable("pageSize") int pageSize,
                                                                             @PathVariable("field") String field) {
        return new ResponseEntity<>(productService.getAllProductsByPage(offSet, pageSize, field),
                HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<List<ProductDto>> getProductById(@PathVariable long id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(new ApiResponse("deleted product successfully", true), HttpStatus.OK);
    }


}
