package com.pratice.product.controller;

import com.pratice.product.dto.ProductDto;
import com.pratice.product.model.Product;
import com.pratice.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;

@RestController
@RequestMapping("api")
public class ProductController {

    public final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get")
    public List<ProductDto> getProduct() {
        return productService.getProduct();
    }


    @PostMapping("/post")
    public Product postProduct(@RequestBody Product product) {
        return productService.postProduct(product);

    }

    @PutMapping("/put/{id}")
    public Product putProduct(@RequestBody Product product, @PathVariable("id") Long id) {
        return productService.putProduct(product, id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }

}
