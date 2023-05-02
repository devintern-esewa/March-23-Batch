package com.inventoryDb.service;

import com.configDb.model.FileDetail;
import com.configDb.repository.FileDetailRepository;
import com.inventoryDb.enums.ProductEnum;
import com.inventoryDb.model.Product;
import com.inventoryDb.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private FileDetailRepository fileDetailRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testConvertCsvDataInFilePathToProduct(){
        // Given
        String filePath = "test.csv";
        String csvData = "name,code,description,quantity,price\n" +
                "Product 1,12345678,Product 1 Description,10,9.99\n" +
                "Product 2,87654321,Product 2 Description,20,19.99\n";


        when(fileDetailRepository.findFileDetailByFilePath(filePath)).thenReturn(new FileDetail());
        when(productRepository.getProductByProductStatusAndCode(any(), any())).thenReturn(Optional.empty());

        // When
        List<Product> products = productService.convertCsvDataInFilePathToProduct(filePath);

        // Then
        assertEquals(2, products.size());
        assertEquals("Product 1", products.get(0).getName());
        assertEquals("12345678", products.get(0).getCode());
        assertEquals(ProductEnum.ACTIVE, products.get(0).getProductStatus());
        assertEquals(10.0, products.get(0).getQuantity());
        assertEquals(9.99, products.get(0).getPrice());
        assertEquals("Product 2", products.get(1).getName());
        assertEquals("87654321", products.get(1).getCode());
        assertEquals(ProductEnum.ACTIVE, products.get(1).getProductStatus());
        assertEquals(20.0, products.get(1).getQuantity());
        assertEquals(19.99, products.get(1).getPrice());
    }

    @Test
    void testProcessProduct() {
        // Given
        String filePath = "test.csv";
        Product product1 = Product.builder().name("Product 1").code("12345678").quantity(10.0).price(9.99).build();
        Product product2 = Product.builder().name("Product 2").code("87654321").quantity(20.0).price(19.99).build();
        List<Product> products = Arrays.asList(product1, product2);

        when(fileDetailRepository.findFileDetailByFilePath(filePath)).thenReturn(new FileDetail());

        // When
        List<Product> processedProducts = productService.processProduct(products, filePath);

        // Then
        assertEquals(2, processedProducts.size());
        assertEquals(product1, processedProducts.get(0));
        assertEquals(product2, processedProducts.get(1));
        verify(productRepository, times(2)).getProductByProductStatusAndCode(ProductEnum.ACTIVE, "12345678");
        verify(productRepository, times(2)).getProductByProductStatusAndCode(ProductEnum.ACTIVE, "87654321");
        verify(fileDetailRepository, times(2)).save(any(FileDetail.class));
    }
}