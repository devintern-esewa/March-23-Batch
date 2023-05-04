package com.inventoryDb.repository;

import com.inventoryDb.dto.ProductDto;
import com.inventoryDb.enums.ProductEnum;
import com.inventoryDb.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testGetProductByProductStatusAndCode() {
        Product product = new Product();
        product.setCode("TEST-001");
        product.setName("Test Product");
        product.setProductStatus(ProductEnum.ACTIVE);
        entityManager.persist(product);
        entityManager.flush();

        Optional<Product> result = productRepository.getProductByProductStatusAndCode(ProductEnum.ACTIVE, "TEST-001");

        assertTrue(result.isPresent());
        assertEquals("Test Product", result.get().getName());
    }

    @Test
    public void testGetAllProductDto() {
        Product product1 = new Product();
        product1.setCode("TEST-001");
        product1.setName("Test Product 1");
        product1.setProductStatus(ProductEnum.ACTIVE);
        entityManager.persist(product1);

        Product product2 = new Product();
        product2.setCode("TEST-002");
        product2.setName("Test Product 2");
        product2.setProductStatus(ProductEnum.INACTIVE);
        entityManager.persist(product2);

        entityManager.flush();

        List<ProductDto> result = productRepository.getAllProductDto();

        assertEquals(1, result.size());
        assertEquals("Test Product 1", result.get(0).getName());
        assertEquals("TEST-001", result.get(0).getCode());
        assertEquals(0, result.get(0).getQuantity());
        assertEquals(0, result.get(0).getPrice());
    }

}