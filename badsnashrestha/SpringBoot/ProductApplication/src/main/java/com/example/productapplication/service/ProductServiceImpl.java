package com.example.productapplication.service;

import com.example.productapplication.controller.ProductController;
import com.example.productapplication.customException.IdAlreadyExistsException;
import com.example.productapplication.customException.IdDoesntExistsException;
import com.example.productapplication.customException.InvalidIdException;
import com.example.productapplication.dto.ProductDto;
import com.example.productapplication.model.Product;
import com.example.productapplication.repo.ProductRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //if product with productId is found, it overrides the product info
        System.out.println(productDto.getProductName());
        Product product;
        if(productDto.getProductName().isEmpty() ||productDto.getPrice()==0.0 ) throw new RuntimeException("Invalid fileds");
        if (!(productDto.getProductName().isEmpty()) && productDto.getPrice()!=0.0) {
            product = productRepo.findById(productDto.getProductId()).orElse(new Product());
        }
        product = objectMapper.convertValue(productDto, Product.class);
        System.out.println(productDto.getProductName());
        logger.info("Adding product " + productDto.getProductName());
        productRepo.saveNewProduct(product.getProductName(), product.getPrice());
        logger.info("Adding product " + productDto.getProductName());
        //productRepo.save(product);

        //if product with productId is found, it returns productId already exists else add product as new product
       /* Optional<Product> product=productRepo.findById(productDto.getProductId());
        if(productDto.getProductId()<=0) throw new InvalidIdException("Invalid productId "+ productDto.getProductId());
       else if(product.isPresent()) throw new IdAlreadyExistsException("productId "+ productDto.getProductId()+ " already exists to add");

        product= Optional.ofNullable(objectMapper.convertValue(productDto, Product.class));*/


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
        Optional<Product> product = productRepo.findById(productId);
        if (productId <= 0) {
            logger.error(" Invalid ProductId " + productId);
            throw new InvalidIdException(" Invalid ProductId " + productId);
        } else if (product.isEmpty()) {
            logger.error("ProductId " + productId + " doesn't exists");
            throw new IdDoesntExistsException("ProductId " + productId + " doesn't exists");
        }
        logger.info("Product with product id " + productId + " is " + product.get());
        return productRepo.findProductById(productId);
    }

    @Override
    public void deleteProductById(Integer productId) {
        Optional<Product> product = productRepo.findById(productId);
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
        Optional<Product> product = productRepo.findById(productDto.getProductId());
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
