package com.product.service;

import com.product.controller.ProductController;
import com.product.dto.ProductDto;
import com.product.enums.ProductEnum;
import com.product.exception.ProductAlreadyExistException;
import com.product.exception.ResourceNotFoundException;
import com.product.model.Product;
import com.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Override
    public ProductDto saveProduct(ProductDto productDto) {

        Optional<Product> checkingProduct = productRepository.findByProductName(productDto.getProductName());
        if (checkingProduct.isPresent()) {
            logger.error("Product already exist in database");
            throw new ProductAlreadyExistException("This product already exist in Database");
        } else {
            Product product = new Product();
            product.setProductName(productDto.getProductName());
            product.setProductCode(productDto.getProductCode());
            product.setProductStatus(ProductEnum.ACTIVE);
            product.setQuantity(productDto.getQuantity());
            product.setPrice(productDto.getPrice());
            productRepository.save(product);
            logger.info("Saved successfully");
        }
        return productDto;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        logger.info("Fetched data successfully");
        return productRepository.getAllProductDto();
    }

    @Override
    public ProductDto getProductById(long id) {
        return productRepository.getProductDtoById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product with id: " + id + " doesn't exist"));
    }

    @Override
    public ProductDto updateProduct(long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Product doesn't exist"));

        product.setProductName(productDto.getProductName());
        product.setProductCode(productDto.getProductCode());
        product.setQuantity(productDto.getQuantity());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
        logger.info("Product updated successfully");

        return productDto;
    }

    @Override
    public void deleteProduct(long id) {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Product doesn't exist"));
        productRepository.delete(product);
        logger.info("Deleted successfully");
    }
}
