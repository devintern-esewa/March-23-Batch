package com.example.mulltipledbconnectiontask.inventory.service;

import com.example.mulltipledbconnectiontask.exception.IdDoesNotExistsException;
import com.example.mulltipledbconnectiontask.fileDetails.model.FileDetails;
import com.example.mulltipledbconnectiontask.fileDetails.repo.FileDetailsRepo;
import com.example.mulltipledbconnectiontask.inventory.dto.ProductResponseDto;
import com.example.mulltipledbconnectiontask.inventory.enums.ProductStatus;
import com.example.mulltipledbconnectiontask.inventory.model.Product;
import com.example.mulltipledbconnectiontask.inventory.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;

    private final FileDetailsRepo fileDetailsRepo;

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public List<ProductResponseDto> getAllProducts(int startingPage) {
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        logger.info("Defining page number and size of product details to be shown");
        PageRequest pageRequest = PageRequest.of(startingPage, 10);

        logger.info("Getting products as per page request and converting it into list");
        List<Product> listOfProduct = productRepo.findAll(pageRequest).stream().toList();

        for (Product product : listOfProduct) {
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto.setProductName(product.getProductName());
            productResponseDto.setCode(product.getCode());
            productResponseDto.setProductStatus(product.getProductStatus());
            productResponseDto.setQuantity(product.getQuantity());
            productResponseDto.setPrice(product.getPrice());

            logger.info("adding productResponseDto into productResponseDtoList");
            productResponseDtoList.add(productResponseDto);
        }
        logger.info("Returning products as per productResponseDto");
        return productResponseDtoList;
    }

    @Override
    @Cacheable(cacheNames = "products", key = "#productId")
    public ProductResponseDto getProductById(Long productId) {
        logger.info("Getting products as per product id");
        Product product = productRepo.findById(productId).orElseThrow(() -> new IdDoesNotExistsException("Product with id " + productId + " does not exists"));

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setProductName(product.getProductName());
        productResponseDto.setCode(product.getCode());
        productResponseDto.setProductStatus(product.getProductStatus());
        productResponseDto.setQuantity(product.getQuantity());
        productResponseDto.setPrice(product.getPrice());

        logger.info("Returning products by id as per productResponseDto");
        return productResponseDto;
    }

    @Override
    public List<Product> readProductDetailsFromFile(String filepath) {
        List<Product> productList = new ArrayList<>();

        String csvSeparator = ",";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line = " ";
            int value = 0;

            logger.info("Reading csv file according to given file path");
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

                    logger.info("Adding product into productList");
                    productList.add(product);
                }
                value++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("Returning list of products after reading product from every csv files");
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

            logger.info("Getting products from the database if database contains same code as current product code");
            Optional<Product> productsByCode = Optional.ofNullable(productRepo.findByCode(product.getCode()));

            if (productsCode.contains(product.getCode()) || productsByCode.isPresent() && productsByCode.get().getProductStatus() == ProductStatus.ACTIVE) {

                logger.info("Incrementing failure count when product with same code exists and product status is active");
                failureCount++;

                logger.info("Getting file details of the given file path");
                fileDetails = fileDetailsRepo.findByFilePath(filePath);

                logger.info("Setting failure count in file details database table");
                fileDetails.setFailureCount(failureCount);

                logger.info("Updating failure count in file details database table of given file path");
                fileDetailsRepo.save(fileDetails);
            } else {

                logger.info("Incrementing success count when product with same code doesn't exists and product status is deleted");
                successCount++;

                logger.info("Adding product in successProducts list");
                successProducts.add(product);

                logger.info("Getting and adding product code in hashSet");
                productsCode.add(product.getCode());

                logger.info("Getting file details of the given file path");
                fileDetails = fileDetailsRepo.findByFilePath(filePath);

                logger.info("Setting success count in file details database table");
                fileDetails.setSuccessCount(successCount);

                logger.info("Updating success count in file details database table of given file path");
                fileDetailsRepo.save(fileDetails);
            }
        }

        logger.info("Returning list of success products have unique code");
        return successProducts;
    }

    @Override
    @CacheEvict(cacheNames = "products", key = "#productId")
    public void deleteProductById(Long productId) {
        logger.info("Getting products as per product id");
        Product product = productRepo.findById(productId).orElseThrow(() -> new IdDoesNotExistsException("Product with id " + productId + " does not exists"));

        logger.info("Setting product status to deleted");
        product.setProductStatus(ProductStatus.DELETED);

        logger.info("Updating product details in products database table");
        productRepo.save(product);
    }
}
