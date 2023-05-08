package com.example.multipledatabaseconnectiontask.inventorydb.service;

import com.example.multipledatabaseconnectiontask.configdb.model.FileDetails;
import com.example.multipledatabaseconnectiontask.configdb.repo.FileDetailsRepository;
import com.example.multipledatabaseconnectiontask.inventorydb.dto.ProductDto;
import com.example.multipledatabaseconnectiontask.inventorydb.model.Product;
import com.example.multipledatabaseconnectiontask.inventorydb.repo.ProductRepository;
import com.example.multipledatabaseconnectiontask.inventorydb.statusenum.ProductStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FileDetailsRepository fileDetailsRepository;

    public ProductServiceImpl(ProductRepository productRepository, FileDetailsRepository fileDetailsRepository) {
        this.productRepository = productRepository;
        this.fileDetailsRepository = fileDetailsRepository;
    }

    @Override
    public List<Product> convertCsvFilePathDataIntoProduct(String filepath) {
        List<Product> products = new ArrayList<>();
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\suman\\Downloads\\checkcsv\\" + filepath));
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                if (split[0].equals("name")) {
                    continue;
                }
                String name = split[0];
                String code = split[1];
                double quantity = Double.parseDouble(split[2]);
                double price = Double.parseDouble(split[3]);

                Product product = Product.builder()
                        .name(name)
                        .code(code)
                        .status(ProductStatusEnum.ACTIVE)
                        .quantity(quantity)
                        .price(price)
                        .build();
                products.add(product);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return products;
    }

    @Override
    public List<Product> processProduct(List<Product> products, String filepath) {
        List<Product> products1 = new ArrayList<>();
        int successCount = 0;
        int failureCount = 0;
        FileDetails fileDetails;
        for (Product processingProduct : products) {
            Optional<Product> productByCodeAndStatus = productRepository.getAllProductByStatusAndCode(
                    ProductStatusEnum.ACTIVE, processingProduct.getCode());
            if (productByCodeAndStatus.isPresent() && processingProduct.getCode().equals(productByCodeAndStatus.get().getCode())) {
                failureCount++;
                fileDetails = fileDetailsRepository.getFileDetailsByFilepath(filepath);
                fileDetails.setFailure_count(failureCount);
                fileDetailsRepository.save(fileDetails);
            } else {
                successCount++;
                fileDetails = fileDetailsRepository.getFileDetailsByFilepath(filepath);
                fileDetails.setSuccess_count(successCount);
                fileDetailsRepository.save(fileDetails);
                products1.add(processingProduct);
            }
        }
        return products1;
    }

    @Override
    public void saveAllProduct(List<Product> product) {
        productRepository.saveAll(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
