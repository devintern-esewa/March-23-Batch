package com.example.springbootcrud.service;

import com.example.springbootcrud.dto.ProductDto;
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
        Product product;
        if (productDto.getProductId() != null)
            product = productRepo.findProductById(productDto.getProductId()).orElse(new Product());
        product = objectMapper.convertValue(productDto, Product.class);
//       productRepo.save(product);
        logger.info("Adding product " + productDto.getProductName());
        productRepo.addNewProduct(product.getProductName(), product.getPrice());
        logger.info("Added product " + productDto.getProductName());

        // In the below code we have to manually pass the id also
        /*Optional<Product> product = productRepo.findById(productDto.getProductId());
        if (productDto.getProductId() <= 0)
            throw new InvalidIdException("Invalid product id " + productDto.getProductId());
        else if (product.isPresent())
            throw new IdAlreadyExistsException("Product id " + productDto.getProductId() + " already exists");
        product = Optional.of(objectMapper.convertValue(productDto, Product.class));
        productRepo.save(product.get());*/
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        Optional<Product> product = productRepo.findProductById(productId);
        if (productId <= 0) {
            logger.error("Invalid product id " + productId);
            throw new InvalidIdException("Invalid product id " + productId);
        } else if (!product.isPresent()) {
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
        } else if (!product.isPresent()) {
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
        } else if (!product.isPresent()) {
            logger.error("Product with id " + productId + " does not exists");
            throw new IdDoesNotExistsException("Product with id " + productId + " does not exists");
        }
        logger.info("Deleting product id " + productId);
        productRepo.deleteProductById(productId);
        logger.info("deleted product id " + productId);
    }
}
