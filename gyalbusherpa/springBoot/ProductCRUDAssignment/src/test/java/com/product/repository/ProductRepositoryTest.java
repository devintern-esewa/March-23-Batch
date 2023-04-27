package com.product.repository;

import com.product.model.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findByProductName_WhenProductNameExists_ReturnProduct() {

        String productName = "Test Product";
        Product product = Product.builder()
                .productName(productName)
                .productCode("TP01")
                .quantity(10)
                .price(100.00)
                .build();

        when(productRepository.findByProductName(productName)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productRepository.findByProductName(productName);

        assertThat(foundProduct).isPresent();
        assertEquals(productName, foundProduct.get().getProductName());

    }

    /*@Test
    public void getAllProductDto_ReturnsAllProducts() {
        // given

        Product product1 = new Product("Test Product 1", "TP001", 10, 100.0);
        Product product2 = new Product("Test Product 2", "TP002", 20, 200.0);
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.flush();

        // when
        List<ProductDto> allProductDto = productRepository.getAllProductDto();

        // then
        assertThat(allProductDto).hasSize(2);
        assertThat(allProductDto.get(0).getProductName()).isEqualTo(product1.getProductName());
        assertThat(allProductDto.get(1).getProductName()).isEqualTo(product2.getProductName());
    }

    @Test
    public void getProductDtoById_WhenProductExists_ReturnProductDto() {
        // given
        Product product = new Product("Test Product", "TP001", 10, 100.0);
        entityManager.persist(product);
        entityManager.flush();

        // when
        Optional<ProductDto> foundProductDto = productRepository.getProductDtoById(product.getId());

        // then
        assertThat(foundProductDto).isPresent();
        assertThat(foundProductDto.get().getProductName()).isEqualTo(product.getProductName());
        assertThat(foundProductDto.get().getProductCode()).isEqualTo(product.getProductCode());
        assertThat(foundProductDto.get().getQuantity()).isEqualTo(product.getQuantity());
        assertThat(foundProductDto.get().getPrice()).isEqualTo(product.getPrice());
    }*/

}