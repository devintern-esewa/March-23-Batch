package com.example.multipledatabaseconnection.productDetails.service;

import com.example.multipledatabaseconnection.exception.IdNotFoundException;
import com.example.multipledatabaseconnection.fileDetails.model.FileDetails;
import com.example.multipledatabaseconnection.fileDetails.repo.FileDetailsRepo;
import com.example.multipledatabaseconnection.productDetails.dto.ProductDetailsResponseDto;
import com.example.multipledatabaseconnection.productDetails.enums.ProductStatus;
import com.example.multipledatabaseconnection.productDetails.model.ProductDetails;
import com.example.multipledatabaseconnection.productDetails.repo.ProductDetailsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ProductDetailsServiceImpl implements ProductDetailsService {
    @Autowired
    ProductDetailsRepo productDetailsRepo;
    @Autowired
    FileDetailsRepo fileDetailsRepo;
    Logger logger = LoggerFactory.getLogger(ProductDetailsServiceImpl.class);

    @Override
    public List<ProductDetailsResponseDto> getAllProductDetails(int startingPage) {
        PageRequest pageRequest = PageRequest.of(startingPage, 10);
        List<ProductDetails> productDetails=productDetailsRepo.findAll(pageRequest).stream().toList();

        List<ProductDetailsResponseDto> productDetailsResponseDtoList=new ArrayList<>();

        for(ProductDetails productDetail:productDetails){
            ProductDetailsResponseDto productDetailsResponseDto=new ProductDetailsResponseDto();
            productDetailsResponseDto.setProductName(productDetail.getProductName());
            productDetailsResponseDto.setProductStatus(productDetail.getProductStatus());
            productDetailsResponseDto.setCode(productDetail.getCode());
            productDetailsResponseDto.setQuantity(productDetail.getQuantity());
            productDetailsResponseDto.setPrice(productDetail.getPrice());

            productDetailsResponseDtoList.add(productDetailsResponseDto);
        }

        return productDetailsResponseDtoList ;
    }

    @Override
    public ProductDetailsResponseDto getProductDetailsById(Long productDetailsId) {
        ProductDetails productDetails = productDetailsRepo.findById(productDetailsId).orElseThrow(() -> new IdNotFoundException("Product with " + productDetailsId + " doesn't exists"));
        ProductDetailsResponseDto productDetailsResponseDto = new ProductDetailsResponseDto();
        productDetailsResponseDto.setProductName(productDetails.getProductName());
        productDetailsResponseDto.setProductStatus(productDetails.getProductStatus());
        productDetailsResponseDto.setCode(productDetails.getCode());
        productDetailsResponseDto.setQuantity(productDetails.getQuantity());
        productDetailsResponseDto.setPrice(productDetails.getPrice());
        return productDetailsResponseDto;
    }

    @Override
    public List<ProductDetails> readCsvInsertIntoProductDetails(String filePath) throws IOException {
        List<ProductDetails> productDetails = new ArrayList<>();
        String csvSeparator = ",";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = " ";
        int value = 0;
        while ((line = reader.readLine()) != null) {
            if (value >= 1) {
                String[] row = line.split(csvSeparator);
                Long id = Long.valueOf(row[0]);
                String productName = row[1];
                ProductStatus productStatus = ProductStatus.ACTIVE;
                String code = row[3];
                int quantity = Integer.valueOf(row[4]);
                double price = Double.parseDouble(row[5]);
                ProductDetails productDetails1 = new ProductDetails(id, productName, productStatus, code, quantity, price);
                productDetails.add(productDetails1);
            }
            value++;
        }

        reader.close();
        logger.info("Returning productDetails as a list after reading productDetails from every file");
        return productDetails;
    }

    @Override
    public List<ProductDetails> processingProduct(List<ProductDetails> productDetails, String filePath) {
        Integer successCount = 0;
        Integer failCount = 0;
        FileDetails fileDetails;
        HashSet<String> productsCode = new HashSet<>();
        List<ProductDetails> successProductDetails = new ArrayList<>();
        for (ProductDetails productDetail : productDetails) {
            Optional<ProductDetails> sameProductDetails = Optional.ofNullable(productDetailsRepo.getByCode(productDetail.getCode()));
            if (productsCode.contains(productDetail.getCode()) || sameProductDetails.isPresent() && sameProductDetails.get().getProductStatus() == ProductStatus.ACTIVE) {
                failCount++;
                fileDetails = fileDetailsRepo.findByFilePath(filePath);
                fileDetails.setFailureCount(failCount);
                fileDetailsRepo.save(fileDetails);

            } else {
                successCount++;
                successProductDetails.add(productDetail);
                productsCode.add(productDetail.getCode());
                fileDetails = fileDetailsRepo.findByFilePath(filePath);
                fileDetails.setSuccessCount(successCount);
                fileDetailsRepo.save(fileDetails);
            }
        }
        logger.info("Return successProductDetails that contain only the productDetails having unique code");
        return successProductDetails;

    }

    @Override
    public List<ProductDetails> addNewProduct(List<ProductDetails> productDetails) {
        logger.info("Saving productDetails to the inventory_db");
        return productDetailsRepo.saveAll(productDetails);
    }


    @Override
    public void deleteProductDetailsById(Long productDetailsId) {
        ProductDetails productDetails = productDetailsRepo.findById(productDetailsId).orElseThrow(() -> new IdNotFoundException("Product with " + productDetailsId + " doesn't exists"));
        productDetails.setProductStatus(ProductStatus.DELETED);
        productDetailsRepo.save(productDetails);
    }


}
