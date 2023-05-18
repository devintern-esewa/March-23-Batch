package com.pratice.product.service;

import com.pratice.product.dto.ProductDto;
import com.pratice.product.model.Product;

import javax.persistence.Id;
import java.util.List;

public interface ProductService {
    List<ProductDto> getProduct();

    Product postProduct(Product product);

    Product putProduct(Product product, Long id);

    String deleteProduct(Long id);


}
