package com.example.mulltipledbconnectiontask.inventory.service;

import com.example.mulltipledbconnectiontask.exception.IdDoesNotExistsException;
import com.example.mulltipledbconnectiontask.fileDetails.model.FileDetails;
import com.example.mulltipledbconnectiontask.fileDetails.repo.FileDetailsRepo;
import com.example.mulltipledbconnectiontask.inventory.dto.ProductResponseDto;
import com.example.mulltipledbconnectiontask.inventory.enums.ProductStatus;
import com.example.mulltipledbconnectiontask.inventory.model.Product;
import com.example.mulltipledbconnectiontask.inventory.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private FileDetailsRepo fileDetailsRepo;

    @Override
    public List<ProductResponseDto> getAllProducts(int startingPage) {
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(startingPage, 10);
        List<Product> listOfProduct = productRepo.findAll(pageRequest).stream().toList();
        for (Product product : listOfProduct) {
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto.setProductName(product.getProductName());
            productResponseDto.setCode(product.getCode());
            productResponseDto.setProductStatus(product.getProductStatus());
            productResponseDto.setQuantity(product.getQuantity());
            productResponseDto.setPrice(product.getPrice());
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;
    }

    @Override
    public ProductResponseDto getProductById(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new IdDoesNotExistsException("Product with id " + productId + " does not exists"));
        ProductResponseDto productResponseDto = new ProductResponseDto();
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
    public List<Product> countSuccessFailureBeforeSavingProducts(List<Product> products, String filePath) {
        List<Product> successProducts = new ArrayList<>();
        HashSet<String> productsCode = new HashSet<>();
        FileDetails fileDetails;
        Long successCount = 0L;
        Long failureCount = 0L;
        for (Product product : products) {
            Optional<Product> productsByCode = Optional.ofNullable(productRepo.findByCode(product.getCode()));
            if (productsCode.contains(product.getCode()) || productsByCode.isPresent() && productsByCode.get().getProductStatus() == ProductStatus.ACTIVE) {
                failureCount++;
                fileDetails = fileDetailsRepo.findByFilePath(filePath);
                fileDetails.setFailureCount(failureCount);
                fileDetailsRepo.save(fileDetails);
            } else {
                successCount++;
                successProducts.add(product);
                productsCode.add(product.getCode());
                fileDetails = fileDetailsRepo.findByFilePath(filePath);
                fileDetails.setSuccessCount(successCount);
                fileDetailsRepo.save(fileDetails);
            }
        }
        return successProducts;
    }

    @Override
    public void deleteProductById(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new IdDoesNotExistsException("Product with id " + productId + " does not exists"));
        product.setProductStatus(ProductStatus.DELETED);
        productRepo.save(product);
    }
}
