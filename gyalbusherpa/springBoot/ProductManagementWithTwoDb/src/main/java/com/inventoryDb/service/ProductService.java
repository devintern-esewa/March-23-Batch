package com.inventoryDb.service;

import com.inventoryDb.model.Product;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ProductService {

    ArrayList<Product> convertFilePathToProduct(String filePath);

    Product saveProduct(List<Product> products);

}
