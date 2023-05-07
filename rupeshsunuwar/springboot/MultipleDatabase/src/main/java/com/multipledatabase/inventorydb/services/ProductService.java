package com.multipledatabase.inventorydb.services;

import com.multipledatabase.inventorydb.dto.ProductDto;
import com.multipledatabase.inventorydb.entity.Product;

import java.util.List;

public interface ProductService {


    public List<ProductDto> getAllProduct();

    public void addAllProduct(List<Product> productList);

    public boolean checkProductStatus(Product product, List<ProductDto> productList);
}
