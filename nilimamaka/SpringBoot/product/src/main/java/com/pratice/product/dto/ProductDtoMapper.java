package com.pratice.product.dto;

import com.pratice.product.model.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductDtoMapper implements Function<Product, ProductDto> {
    @Override
    public ProductDto apply(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName()
        );
    }
}

