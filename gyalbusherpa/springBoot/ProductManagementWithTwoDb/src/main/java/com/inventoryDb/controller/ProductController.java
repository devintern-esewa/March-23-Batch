package com.inventoryDb.controller;

import com.inventoryDb.dto.ProductDto;
import com.inventoryDb.payload.ApiResponse;
import com.inventoryDb.service.ProductService;
import org.springframework.data.domain.Page;
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
        return new ResponseEntity<List<ProductDto>>(productService.getAllProducts(), HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("deleted product successfully", true), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<Page<ProductDto>> getAllProductsByPagingAndSorting(int offSet, int pageSize, String field){
        return new ResponseEntity<Page<ProductDto>>(productService.getAllProductsByPage(offSet,pageSize,field),HttpStatus.OK);
    }

}
