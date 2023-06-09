package com.inventoryDb.service;

import com.inventoryDb.dto.ProductDto;
import com.inventoryDb.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> convertCsvDataInFilePathToProduct(String filePath);

    List<Product> processProduct(List<Product> products, String filepath);

    void saveProduct(List<Product> products);

    List<ProductDto> getAllProducts();

    void deleteProduct(long id);

    List<ProductDto> getAllProductsByPage(int offSet, int pageSize, String field);

    List<ProductDto> getProductById(long id);
}
