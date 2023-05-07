package com.restapi.product.service;


import com.restapi.product.customexception.DataProvidedInvalidException;
import com.restapi.product.customexception.ProductAlreadyExistException;
import com.restapi.product.customexception.ProductDoesNotExistException;
import com.restapi.product.dao.ProductRepository;
import com.restapi.product.dto.ProductDto;
import com.restapi.product.enums.ProductEnum;
import com.restapi.product.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@EnableCaching
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;
    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


    public List<ProductDto> getAllProducts() {


        List<Product> products = new ArrayList<>();
        List<ProductDto> productDto = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        for (Product product : products) {

            productDto.add(convertToProductDto(Optional.ofNullable(product)));
        }
        logger.info("Getting All the Products from the Database");
        return productDto;
    }

    public static ProductDto convertToProductDto(Optional<Product> product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductName(product.get().getProductName());
        productDto.setProductCategory(product.get().getProductCategory());
        productDto.setProductPrice(product.get().getProductPrice());
        productDto.setProductQuality(product.get().getProductQuality());
        return productDto;


    }

    public static Product convertToProduct(ProductDto productDto) {

        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductCategory(productDto.getProductCategory());
        product.setProductQuality(productDto.getProductQuality());
        product.setProductStatus(ProductEnum.Active);
        return product;
    }


    @Cacheable(cacheNames = "product",key ="#name")
    public ProductDto getProduct(String productName) {

        Optional<Product> product = productRepository.findByProductName(productName);
        logger.info("Getting the product");
        if (product.isPresent()) {
            return convertToProductDto(product);

        } else {
            logger.error("Throwing Exception because id doesnt exist.");
            throw new ProductDoesNotExistException("Invalid id");
        }
    }

    public ProductDto addProduct(ProductDto productDto) {

        if (productDto.getProductPrice() < 0) {
            logger.error("Provided Id Invalid Exception");
            throw new DataProvidedInvalidException("Data Invalid");
        } else if (!productRepository.findByProductName(productDto.getProductName()).isPresent()){
            productRepository.save(convertToProduct(productDto));
            logger.info("Added  product in database");
            return productDto;

        } else {
            logger.info("Product Already Exist Exception");
            throw new ProductAlreadyExistException("Product Already Exist");
        }

    }


    @CachePut(cacheNames ="product",key="#name")
    public ProductDto updateProduct(String name, ProductDto productDto) {

        if (productRepository.findByProductName(name).isPresent()) {
            logger.info("Converting productDto to product");
            Product product = convertToProduct(productDto);
            logger.info("Converted productDto to product");
            productRepository.save(product);
            logger.info("Save product in database");
            return productDto;
        } else logger.error("Doesnt Exist Exception");
        throw new ProductDoesNotExistException("Does Not exist");
    }


    @CacheEvict(cacheNames="product",key="#name")
    public boolean deleteProduct(String name) {

        if (productRepository.findByProductName(name).isPresent()) {
            logger.info("Product of this id exist");
            productRepository.deleteByProductName(name);
            logger.info("product deleted");
            return true;
        } else logger.error("Product Doest not exist");
        throw new ProductDoesNotExistException("Does Not exist");
    }

}
