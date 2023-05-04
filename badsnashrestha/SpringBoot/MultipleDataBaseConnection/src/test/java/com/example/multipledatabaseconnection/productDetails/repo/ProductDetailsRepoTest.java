package com.example.multipledatabaseconnection.productDetails.repo;

import com.example.multipledatabaseconnection.productDetails.enums.ProductStatus;
import com.example.multipledatabaseconnection.productDetails.model.ProductDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ProductDetailsRepoTest {
    @Autowired
    private ProductDetailsRepo productDetailsRepo;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void whenGetByCode_ThenReturnProductDetails() {
        ProductDetails productDetails = new ProductDetails(1L, "Apple iPhone XR", ProductStatus.ACTIVE, "IPH-XR-BLK", 50, 699.99);
        testEntityManager.persist(productDetails);
        testEntityManager.flush();

        Optional<ProductDetails> productDetails1 = Optional.ofNullable(productDetailsRepo.getByCode("IPH-XR-BLK"));

        assertTrue(productDetails1.isPresent());
        assertEquals("Apple iPhone XR", productDetails1.get().getProductName());

    }

}