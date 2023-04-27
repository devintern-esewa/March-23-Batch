package com.product.service;

import com.product.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto saveProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(long id);

    ProductDto updateProduct(long id, ProductDto productDto);

    void deleteProduct(long id);

    ProductDto getByProductName(String productName);
}
