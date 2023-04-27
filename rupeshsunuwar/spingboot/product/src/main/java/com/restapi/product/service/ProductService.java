package com.restapi.product.service;

import com.restapi.product.dto.ProductDto;

import java.util.List;

public interface ProductService {

    public List<ProductDto> getAllProducts();

    public ProductDto getProduct(Integer id);

    public ProductDto addProduct(ProductDto productDto);

    public ProductDto updateProduct(Integer productId, ProductDto productDto);

    public boolean deleteProduct(Integer id);


}
