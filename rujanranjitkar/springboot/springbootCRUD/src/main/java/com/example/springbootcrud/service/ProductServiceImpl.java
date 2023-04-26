package com.example.springbootcrud.service;

import com.example.springbootcrud.dto.ProductDto;
import com.example.springbootcrud.exception.EmptyFieldException;
import com.example.springbootcrud.exception.IdAlreadyExistsException;
import com.example.springbootcrud.exception.IdDoesNotExistsException;
import com.example.springbootcrud.exception.InvalidIdException;
import com.example.springbootcrud.model.Product;
import com.example.springbootcrud.repo.ProductRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final ObjectMapper objectMapper;

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public List<Product> getAllProducts() {
        logger.info("Selecting all products from database: " + productRepo.findAllProducts().size());
        return productRepo.findAllProducts();
    }

    @Override
    public void addNewProduct(ProductDto productDto) {
        Optional<Product> product = productRepo.findProductByName(productDto.getProductName());
        if (productDto.getProductName().isEmpty() || productDto.getPrice() == 0.0) {
            logger.error("Empty fields are not allowed");
            throw new EmptyFieldException("Empty fields are not allowed");
        } else if (product.isPresent()) {
            Product existingProduct = product.get();
            logger.info("Price of " + existingProduct.getProductName() + " before updating " + existingProduct.getPrice());
            existingProduct.setPrice(productDto.getPrice() + existingProduct.getPrice());
            logger.info("Adding existing product after updating price " + existingProduct.getProductName() + ", " + existingProduct.getPrice());
            productRepo.save(existingProduct);
            logger.info("Added existing product " + existingProduct.getProductName() + ", " + existingProduct.getPrice());
        } else {
            product = Optional.of(objectMapper.convertValue(productDto, Product.class));
            logger.info("Adding product " + productDto.getProductName());
            productRepo.addNewProduct(product.get().getProductName(), product.get().getPrice());
            logger.info("Added product " + productDto.getProductName());
        }
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        Optional<Product> product = productRepo.findProductById(productId);
        if (productId <= 0) {
            logger.error("Invalid product id " + productId);
            throw new InvalidIdException("Invalid product id " + productId);
        } else if (product.isEmpty()) {
            logger.error("Product with id " + productId + " does not exists");
            throw new IdDoesNotExistsException("Product with id " + productId + " does not exists");
        }
        logger.info("Selecting product by product id " + productId + ": " + product.get().getProductName() + "," + product.get().getPrice());
        return product;
    }

    @Override
    public void updateProduct(ProductDto productDto) {
        Optional<Product> product = productRepo.findProductById(productDto.getProductId());
        if (productDto.getProductId() <= 0) {
            logger.error("Invalid product id " + productDto.getProductId());
            throw new InvalidIdException("Invalid product id " + productDto.getProductId());
        } else if (product.isEmpty()) {
            logger.error("Product with id " + productDto.getProductId() + " does not exists");
            throw new IdDoesNotExistsException("Product with id " + productDto.getProductId() + " does not exists");
        }
        Product existingProduct = product.get();
        existingProduct.setProductName(productDto.getProductName());
        existingProduct.setPrice(productDto.getPrice());
//        productRepo.save(existingProduct);
        logger.info("Updating product id: " + existingProduct.getProductId());
        productRepo.updateProduct(existingProduct.getProductId(), existingProduct.getProductName(), existingProduct.getPrice());
        logger.info("Updated product id " + existingProduct.getProductId() + " with product name " + existingProduct.getProductName() + " and price " + existingProduct.getPrice());
    }

    @Override
    public void deleteProductById(Long productId) {
        Optional<Product> product = productRepo.findProductById(productId);
        if (productId <= 0) {
            logger.error("Invalid product id " + productId);
            throw new InvalidIdException("Invalid product id " + productId);
        } else if (product.isEmpty()) {
            logger.error("Product with id " + productId + " does not exists");
            throw new IdDoesNotExistsException("Product with id " + productId + " does not exists");
        }
        logger.info("Deleting product id " + productId);
        productRepo.deleteProductById(productId);
        logger.info("deleted product id " + productId);
    }
}
