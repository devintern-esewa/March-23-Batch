package com.multipledatabase.inventorydb.services;

import com.multipledatabase.inventorydb.dto.ProductDto;
import com.multipledatabase.inventorydb.entity.Product;

import java.util.List;

public interface ProductService {


    public ProductDto findByName(String name);

    public List<ProductDto> getAllProduct();

    public boolean addAllProduct(List<Product> productList);

    public boolean checkProductStatus(Product product, List<ProductDto> productList);

    public boolean addProduct(ProductDto productDto);
}
