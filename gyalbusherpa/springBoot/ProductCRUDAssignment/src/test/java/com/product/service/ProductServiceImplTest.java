package com.product.service;

import com.product.dto.ProductDto;
import com.product.enums.ProductEnum;
import com.product.exception.ProductAlreadyExistException;
import com.product.exception.ResourceNotFoundException;
import com.product.model.Product;
import com.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void saveProduct_whenProductAlreadyExists_shouldThrowException() {
        String productName = "phone";

        Product product = Product.builder()
                .productName("phone")
                .productCode("TP01")
                .quantity(10)
                .price(100.00)
                .build();

        when(productRepository.findByProductName(productName)).thenReturn(Optional.of(product));

        ProductDto productDto = ProductDto.builder()
                .productName(product.getProductName())
                .productCode(product.getProductCode())
                .quantity(product.getQuantity())
                .build();

        assertThrows(ProductAlreadyExistException.class, () -> productService.saveProduct(productDto));
        verify(productRepository, times(1)).findByProductName(productName);
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void saveProduct_whenProductDoesNotExist_shouldSaveProduct() {

        Product product = Product.builder()
                .productName("phone")
                .productCode("TP01")
                .quantity(10)
                .price(100.00)
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDto productDto = ProductDto.builder()
                .productName(product.getProductName())
                .productCode(product.getProductCode())
                .quantity(product.getQuantity())
                .build();

        assertNotNull(productService.saveProduct(productDto));
        assertEquals(product.getProductName(), productService.saveProduct(productDto).getProductName());

        verify(productRepository, times(2)).findByProductName(productDto.getProductName());
        verify(productRepository, times(2)).save(any(Product.class));
    }

    @Test
    void getAllProducts_shouldReturnListOfProductDto() {
        List<ProductDto> productDtoList = new ArrayList<>();

        ProductDto productDto1 = ProductDto.builder()
                .productName("Test Product")
                .productCode("T1")
                .quantity(20)
                .price(10)
                .build();

        productDtoList.add(productDto1);

        ProductDto productDto2 = ProductDto.builder()
                .productName("Test Product 2")
                .productCode("T12")
                .quantity(205)
                .price(101)
                .build();

        productDtoList.add(productDto2);

        when(productRepository.getAllProductDto()).thenReturn(productDtoList);

        assertEquals(productDtoList.size(), productService.getAllProducts().size());
        assertEquals(productDto1.getProductName(), productService.getAllProducts().get(0).getProductName());
        assertEquals(productDto2.getProductName(), productService.getAllProducts().get(1).getProductName());
    }

    @Test
    public void getProductById_whenProductIdExists_shouldReturnProductDto() {
        long productId = 1L;

        ProductDto productDto = ProductDto.builder()
                .productName("Product 1")
                .productCode("P001")
                .quantity(10)
                .price(100.0)
                .build();

        when(productRepository.getProductDtoById(productId))
                .thenReturn(Optional.of(productDto));

        ProductDto fetchedProduct = productService.getProductById(productId);

        assertNotNull(fetchedProduct);
        assertEquals(productDto.getProductName(), fetchedProduct.getProductName());
        assertEquals(productDto.getProductCode(), fetchedProduct.getProductCode());
        assertEquals(productDto.getQuantity(), fetchedProduct.getQuantity());
        assertEquals(productDto.getPrice(), fetchedProduct.getPrice(), 0.0);
    }

    @Test
    void getProductById_whenProductIdDoesNotExist_shouldThrowResourceNotFoundException() {
        when(productRepository.getProductDtoById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(1L));
        verify(productRepository, times(1)).getProductDtoById(1L);
    }

    @Test
    public void updateProduct_whenProductExists_shouldUpdateProduct() {
        long productId = 1L;
        ProductDto productDto = ProductDto.builder()
                .productName("Product 1 Updated")
                .productCode("P001U")
                .quantity(20)
                .price(200.0)
                .build();

        Product existingProduct = Product.builder()
                .id(productId)
                .productName("Product 1")
                .productCode("P001U")
                .productStatus(ProductEnum.ACTIVE)
                .quantity(10)
                .price(100.0)
                .build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));


        ProductDto updatedProductDto = productService.updateProduct(productId, productDto);

        assertNotNull(updatedProductDto);
        assertEquals(productDto.getProductName(), updatedProductDto.getProductName());
        assertEquals(productDto.getProductCode(), updatedProductDto.getProductCode());
        assertEquals(productDto.getQuantity(), updatedProductDto.getQuantity());
        assertEquals(productDto.getPrice(), updatedProductDto.getPrice());

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    public void updateProduct_whenProductDoNotExist_shouldThrowResourceNotFoundException() {
        // Arrange
        long productId = 1L;
        ProductDto productDto = ProductDto.builder()
                .productName("Product 1 Updated")
                .productCode("P001U")
                .quantity(20)
                .price(200.0)
                .build();

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.updateProduct(productId, productDto));

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    public void deleteProduct_whenProductIdExist_shouldDeleteProduct() {
        long productId = 1L;

        Product product = Product.builder()
                .productName("phone")
                .productCode("TP01")
                .quantity(10)
                .productStatus(ProductEnum.ACTIVE)
                .price(100.00)
                .build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    public void deleteProduct_whenProductIdDoNotExist_shouldThrowException() {

        long invalidProductId = 999L;

        when(productRepository.findById(invalidProductId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.deleteProduct(invalidProductId));

        verify(productRepository, times(1)).findById(invalidProductId);
        verify(productRepository, never()).delete(any());
    }

}