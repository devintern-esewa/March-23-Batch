package com.example.mulltipledbconnectiontask.inventory.service;

import com.example.mulltipledbconnectiontask.exception.IdDoesNotExistsException;
import com.example.mulltipledbconnectiontask.inventory.dto.ProductResponseDto;
import com.example.mulltipledbconnectiontask.inventory.enums.ProductStatus;
import com.example.mulltipledbconnectiontask.inventory.model.Product;
import com.example.mulltipledbconnectiontask.inventory.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public ProductResponseDto getProductById(Long productId) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        Product product = productRepo.findById(productId).orElseThrow(() -> new IdDoesNotExistsException("Product with id " + productId + " does not exists"));
        productResponseDto.setProductName(product.getProductName());
        productResponseDto.setCode(product.getCode());
        productResponseDto.setProductStatus(product.getProductStatus());
        productResponseDto.setQuantity(product.getQuantity());
        productResponseDto.setPrice(product.getPrice());
        return productResponseDto;
    }

    @Override
    public List<Product> readProductDetailsFromFile(String filepath) {
        List<Product> productList = new ArrayList<>();
        String csvFile = filepath;
        String csvSeparator = ",";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            String line = " ";
            int value = 0;
            while ((line = reader.readLine()) != null) {
                if (value >= 1) {
                    String[] row = line.split(csvSeparator);
                    String name = row[1];
                    String code = row[3];
                    Long quantity = Long.parseLong(row[4]);
                    double price = Double.parseDouble(row[5]);

                    Product product = new Product();
                    product.setProductName(name);
                    product.setProductStatus(ProductStatus.ACTIVE);
                    product.setCode(code);
                    product.setQuantity(quantity);
                    product.setPrice(price);
                    productList.add(product);
                }
                value++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> saveProducts(List<Product> products) {
        return productRepo.saveAll(products);
    }

    @Override
    public void deleteProductById(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new IdDoesNotExistsException("Product with id " + productId + " does not exists"));
        product.setProductStatus(ProductStatus.DELETED);
        productRepo.save(product);
    }
}
