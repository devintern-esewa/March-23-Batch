package com.restapi.product.service;

import com.restapi.product.dto.ProductDto;

import java.util.List;

public interface ProductService {

    public List<ProductDto> getAllProducts();

    public ProductDto getProduct(String productName);

    public ProductDto addProduct(ProductDto productDto);

    public ProductDto updateProduct(String productName, ProductDto productDto);

    public boolean deleteProduct(String productName);


}
