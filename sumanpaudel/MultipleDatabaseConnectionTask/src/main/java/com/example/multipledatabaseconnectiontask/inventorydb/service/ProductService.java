package com.example.multipledatabaseconnectiontask.inventorydb.service;

import com.example.multipledatabaseconnectiontask.inventorydb.dto.ProductDto;
import com.example.multipledatabaseconnectiontask.inventorydb.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> convertCsvFilePathDataIntoProduct(String filepath);
    List<Product> processProduct(List<Product> products,String filepath);
    void saveAllProduct(List<Product> product);
    List<Product> getAllProduct();
}
