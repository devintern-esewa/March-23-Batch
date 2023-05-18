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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
        logger.info("Defining the page number and the size of productDetails to be shown");
        PageRequest pageRequest = PageRequest.of(startingPage, 10);

        logger.info("Getting ProductDetails as per pageRequest and converting it into list");
        List<ProductDetails> productDetails = productDetailsRepo.findAll(pageRequest).stream().toList();

        List<ProductDetailsResponseDto> productDetailsResponseDtoList = new ArrayList<>();

        for (ProductDetails productDetail : productDetails) {
            ProductDetailsResponseDto productDetailsResponseDto = new ProductDetailsResponseDto();
            productDetailsResponseDto.setProductName(productDetail.getProductName());
            productDetailsResponseDto.setProductStatus(productDetail.getProductStatus());
            productDetailsResponseDto.setCode(productDetail.getCode());
            productDetailsResponseDto.setQuantity(productDetail.getQuantity());
            productDetailsResponseDto.setPrice(productDetail.getPrice());

            logger.info("Adding productDetailsResponseDto into productDetailsResponseDtoList ");
            productDetailsResponseDtoList.add(productDetailsResponseDto);
        }

        logger.info("Returning productDetails as per productDetailsResponseDto");
        return productDetailsResponseDtoList;
    }

    @Override
    @Cacheable(cacheNames = "products", key = "#productDetailsId")
    public ProductDetailsResponseDto getProductDetailsById(Long productDetailsId) {

        logger.info("Getting productDetails as per the productDetailsId");
        ProductDetails productDetails = productDetailsRepo.findById(productDetailsId).orElseThrow(() -> new IdNotFoundException("Product with " + productDetailsId + " doesn't exists"));

        ProductDetailsResponseDto productDetailsResponseDto = new ProductDetailsResponseDto();

        productDetailsResponseDto.setProductName(productDetails.getProductName());
        productDetailsResponseDto.setProductStatus(productDetails.getProductStatus());
        productDetailsResponseDto.setCode(productDetails.getCode());
        productDetailsResponseDto.setQuantity(productDetails.getQuantity());
        productDetailsResponseDto.setPrice(productDetails.getPrice());

        logger.info("Returning productDetailsById as per productDetailsResponseDto");
        return productDetailsResponseDto;
    }

    @Override
    public List<ProductDetails> readCsvInsertIntoProductDetails(String filePath) throws IOException {
        List<ProductDetails> productDetailsList = new ArrayList<>();

        String csvSeparator = ",";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = " ";
        int value = 0;

        logger.info("Reading csv file according to filePath");
        while ((line = reader.readLine()) != null) {
            if (value >= 1) {
                String[] row = line.split(csvSeparator);
                Long id = Long.valueOf(row[0]);
                String productName = row[1];
                ProductStatus productStatus = ProductStatus.ACTIVE;
                String code = row[3];
                int quantity = Integer.valueOf(row[4]);
                double price = Double.parseDouble(row[5]);

                ProductDetails productDetails = new ProductDetails(id, productName, productStatus, code, quantity, price);

                logger.info("Adding productDetails read from file in productDetailsList");
                productDetailsList.add(productDetails);
            }
            value++;
        }

        reader.close();
        logger.info("Returning productDetails as a list after reading productDetails from every file");
        return productDetailsList;
    }

    @Override
    public List<ProductDetails> processingProduct(List<ProductDetails> productDetails, String filePath) {
        Integer successCount = 0;
        Integer failCount = 0;
        FileDetails fileDetails;

        HashSet<String> productsCode = new HashSet<>();
        List<ProductDetails> successProductDetails = new ArrayList<>();

        for (ProductDetails productDetail : productDetails) {

            logger.info("Getting the productDetails from the database if database contains same code as current productCode");
            Optional<ProductDetails> sameProductDetails = Optional.ofNullable(productDetailsRepo.getByCode(productDetail.getCode()));

            if (productsCode.contains(productDetail.getCode()) || sameProductDetails.isPresent() && sameProductDetails.get().getProductStatus() == ProductStatus.ACTIVE) {

                logger.info("Incrementing failCount when productDetails with same code exists and it status  is active in table ");
                failCount++;

                logger.info("Getting fileDetails of the given filePath");
                fileDetails = fileDetailsRepo.findByFilePath(filePath);

                logger.info("Setting the failureCount in fileDetails table");
                fileDetails.setFailureCount(failCount);

                logger.info("Updating failureCount in fileDetails table of given filePath");
                fileDetailsRepo.save(fileDetails);

            } else {
                logger.info("Incrementing successCount when productDetails with same code doesn't exists and it status is deleted in table ");
                successCount++;

                logger.info("Adding productDetail into successProductDetails list");
                successProductDetails.add(productDetail);

                logger.info("Getting and adding code of productDetail in Hash Set");
                productsCode.add(productDetail.getCode());

                logger.info("Getting fileDetails of the given filePath");
                fileDetails = fileDetailsRepo.findByFilePath(filePath);

                logger.info("Setting the successCount in fileDetails table");
                fileDetails.setSuccessCount(successCount);

                logger.info("Updating successCount in fileDetails table of given filePath");
                fileDetailsRepo.save(fileDetails);
            }
        }

        logger.info("Return list of successProductDetails that contain only the productDetails having unique code");
        return successProductDetails;

    }

    @Override
    public List<ProductDetails> addNewProduct(List<ProductDetails> productDetails) {
        logger.info("Saving productDetails to the inventory_db");
        return productDetailsRepo.saveAll(productDetails);
    }


    @Override
    @CacheEvict(cacheNames = "products",key = "#productDetailsId")
    public void deleteProductDetailsById(Long productDetailsId) {

        logger.info("Getting productDetails as per the productDetailsId");
        ProductDetails productDetails = productDetailsRepo.findById(productDetailsId).orElseThrow(() -> new IdNotFoundException("Product with " + productDetailsId + " doesn't exists"));

        logger.info("Setting productStatus to deleted");
        productDetails.setProductStatus(ProductStatus.DELETED);

        logger.info("Updating productDetails in productDetails table");
        productDetailsRepo.save(productDetails);
    }


}
