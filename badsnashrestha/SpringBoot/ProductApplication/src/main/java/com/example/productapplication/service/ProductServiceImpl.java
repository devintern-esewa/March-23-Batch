package com.example.productapplication.service;

import com.example.productapplication.controller.ProductController;
import com.example.productapplication.customException.IdDoesntExistsException;
import com.example.productapplication.customException.EmptyFieldException;
import com.example.productapplication.customException.InvalidIdException;
import com.example.productapplication.dto.ProductDto;
import com.example.productapplication.model.Product;
import com.example.productapplication.repo.ProductRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    public final ProductRepo productRepo;
    public final ObjectMapper objectMapper;
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo, ObjectMapper objectMapper) {
        this.productRepo = productRepo;
        this.objectMapper = objectMapper;
    }

    public List<Product> getAllProducts() {
        logger.info("Total products in database " + productRepo.findAllProduct().size());
        return productRepo.findAllProduct();
    }

    public void addNewProduct(ProductDto productDto) {
        Optional<Product> product=productRepo.findProductByName(productDto.getProductName());
        if(productDto.getProductName().isEmpty() ||productDto.getPrice()==0.0 ){
            logger.error("Empty field "+productDto.getProductName() + " "+ productDto.getPrice());
            throw new EmptyFieldException("Empty field");
        }
        if (product.isPresent()){
            Product existingProduct = product.get();
            logger.warn("Product you are trying to add already exists "+product.get().getProductName());
            logger.info("Price of existing product "+existingProduct.getProductName()+" " + existingProduct.getPrice());
            existingProduct.setPrice(productDto.getPrice()+ existingProduct.getPrice());
            logger.info("Updated  price of the product "+ existingProduct.getProductName()+" "+existingProduct.getPrice());
            productRepo.save(existingProduct);
        }
        else{
            product = Optional.ofNullable(objectMapper.convertValue(productDto, Product.class));
            logger.info("Adding product " + productDto.getProductName());
            productRepo.saveNewProduct(productDto.getProductName(), productDto.getPrice());
            logger.info("Adding product " + productDto.getProductName());
        }

    }

    public void addNewProductList(List<ProductDto> productDto) {
        for (ProductDto productDto1 : productDto) {
            Product product = new Product();
            product.setProductName(productDto1.getProductName());
            product.setPrice(productDto1.getPrice());
            productRepo.save(product);
        }
    }

    @Override
    public Optional<Product> getProductById(Integer productId) {
        Optional<Product> product = productRepo.findProductById(productId);
        if (productId <= 0) {
            logger.error(" Invalid ProductId " + productId);
            throw new InvalidIdException(" Invalid ProductId " + productId);
        } else if (product.isEmpty()) {
            logger.error("ProductId " + productId + " doesn't exists");
            throw new IdDoesntExistsException("ProductId " + productId + " doesn't exists");
        }
        return product;
    }

    @Override
    public void deleteProductById(Integer productId) {
        Optional<Product> product = productRepo.findProductById(productId);
        if (productId <= 0) {
            logger.error("Invalid productId " + productId);
            throw new InvalidIdException("Invalid productId " + productId);
        } else if (product.isEmpty()) {
            logger.error("ProductId " + productId + " doesn't exists");
            throw new IdDoesntExistsException("ProductId " + productId + " doesn't exists");
        }
        logger.info("Deleted product is " + product.get().getProductName());
        productRepo.deleteProductById(productId);
    }

    @Override
    public void updateProduct(ProductDto productDto) {
        Optional<Product> product = productRepo.findProductById(productDto.getProductId());
        if (productDto.getProductId() <= 0) {
            logger.error("Invalid ProductId " + productDto.getProductId());
            throw new InvalidIdException("Invalid ProductId " + productDto.getProductId());
        }
        if (product.isEmpty()) {
            logger.error("ProductId " + productDto.getProductId() + " doesn't exists");
            throw new IdDoesntExistsException("ProductId " + productDto.getProductId() + " doesn't exists");
        }

        Product existingProduct = product.get();
        existingProduct.setProductName(productDto.getProductName());
        existingProduct.setPrice(productDto.getPrice());
        logger.info("Updated productName is " + existingProduct.getProductName());
        productRepo.updateProduct(existingProduct.getProductId(), existingProduct.getProductName(), existingProduct.getPrice());
    }


}
