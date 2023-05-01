package com.inventoryDb.service;

import com.inventoryDb.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> convertCsvDataInFilePathToProduct(String filePath);

    List<Product> processProduct(List<Product> products, String filepath);

    void saveProduct(List<Product> products);



}
