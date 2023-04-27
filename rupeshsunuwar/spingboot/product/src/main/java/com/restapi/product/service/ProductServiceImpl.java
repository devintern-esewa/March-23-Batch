package com.restapi.product.service;


import com.restapi.product.customexception.DataProvidedInvalidException;
import com.restapi.product.customexception.IdAlreadyExistException;
import com.restapi.product.customexception.IdDoesNotExistException;
import com.restapi.product.customexception.ProductDoesNotExistException;
import com.restapi.product.dao.ProductRepository;
import com.restapi.product.dto.ProductDto;
import com.restapi.product.enums.ProductEnum;
import com.restapi.product.model.Product;
import com.sun.javafx.util.Logging;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;
    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


    public List<ProductDto> getAllProducts() {


        List<Product> products = new ArrayList<>();
        List<ProductDto> productDto = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        for (Product product : products) {

            productDto.add(createProductDto(Optional.ofNullable(product)));
        }
        logger.info("Getting All the Products from the Database");
        return productDto;
    }

    public static ProductDto createProductDto(Optional<Product> product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.get().getProductId());
        productDto.setProductName(product.get().getProductName());
        productDto.setProductCategory(product.get().getProductCategory());
        productDto.setProductPrice(product.get().getProductPrice());
        productDto.setProductQuality(product.get().getProductQuality());
        return productDto;


    }

    public static Product createProduct(ProductDto productDto) {

        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductCategory(productDto.getProductCategory());
        product.setProductQuality(productDto.getProductQuality());
        product.setProductStatus(ProductEnum.Active);
        return product;
    }

    public ProductDto getProduct(Integer id) {

        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            logger.info("Getting the product");
            return createProductDto(product);

        } else {
            logger.error("Throwing Exception because id doesnt exist.");
            throw new IdDoesNotExistException("Invalid id");
        }
    }

    public ProductDto addProduct(ProductDto productDto) {

        if (productDto.getProductPrice() < 0 || productDto.getProductId() < 0) {
            logger.error("Provided Id Invalid Exception");
            throw new DataProvidedInvalidException("Data Invalid");
        } else if (!productRepository.findById(productDto.getProductId()).isPresent()) {
            productRepository.save(createProduct(productDto));
            logger.info("Added  product in database");
            return productDto;

        } else {
            logger.info("Id Already Exist Exception");
            throw new IdAlreadyExistException("Id Already Exist");
        }

    }

    public ProductDto updateProduct(Integer productId, ProductDto productDto) {

        if (productRepository.findById(productId).isPresent()) {
            logger.info("Converting productDto to product");
            Product product = createProduct(productDto);
            logger.info("Converted productDto to product");
            productRepository.save(product);
            logger.info("Save product in database");
            return productDto;
        } else logger.error("Doesnt Exist Exception");
        throw new ProductDoesNotExistException("Does Not exist");
    }

    public boolean deleteProduct(Integer id) {

        if (productRepository.findById(id).isPresent()) {
            logger.info("Product of this id exist");
            productRepository.deleteById(id);
            logger.info("product deleted");
            return true;
        } else logger.error("Product Doest not exist");
        throw new ProductDoesNotExistException("Does Not exist");
    }

}
