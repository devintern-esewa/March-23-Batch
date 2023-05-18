package com.example.mulltipledbconnectiontask.inventory.service;

import com.example.mulltipledbconnectiontask.fileDetails.model.FileDetails;
import com.example.mulltipledbconnectiontask.fileDetails.repo.FileDetailsRepo;
import com.example.mulltipledbconnectiontask.inventory.enums.ProductStatus;
import com.example.mulltipledbconnectiontask.inventory.model.Product;
import com.example.mulltipledbconnectiontask.inventory.repo.ProductRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    public ProductRepo productRepo;

    @Mock
    public FileDetailsRepo fileDetailsRepo;

    @InjectMocks
    public ProductServiceImpl productService;

    @Test
    @DisplayName("Testing for reading product details from csv file")
    public void readProductDetailsFromFileTest() {
        String filePath = "src/test/resources/product1.csv";

        List<Product> productList = productService.readProductDetailsFromFile(filePath);

        assertAll(
                () -> assertEquals(12, productList.size()),
                () -> assertEquals("Apple iPhone XR", productList.get(0).getProductName()),
                () -> assertEquals("IPH-XR-BLK", productList.get(0).getCode()),
                () -> assertEquals(ProductStatus.ACTIVE, productList.get(0).getProductStatus()),
                () -> assertEquals(50, productList.get(0).getQuantity()),
                () -> assertEquals(699.99, productList.get(0).getPrice())
        );
    }

    @Test
    @DisplayName("Testing of success and failure count before inserting products to inventory database table")
    public void countSuccessFailureBeforeSavingProductsTest() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "Apple iPhone XR", "IPH-XR-BLK", ProductStatus.ACTIVE, 50L, 799.99));
        productList.add(new Product(2L, "Apple iPhone X", "IPH-XR-BLU", ProductStatus.ACTIVE, 50L, 699.99));
        productList.add(new Product(3L, "Apple iPhone 14", "IPH-XR-RED", ProductStatus.ACTIVE, 50L, 899.99));

        FileDetails fileDetails = new FileDetails();
        fileDetails.setFileDetailsId(1L);
        fileDetails.setFilePath("test.csv");
        fileDetails.setSuccessCount(0L);
        fileDetails.setFailureCount(0L);

        when(fileDetailsRepo.findByFilePath("test.csv")).thenReturn(fileDetails);

        when(productRepo.findByCode("IPH-XR-BLK")).thenReturn(productList.get(0));

        List<Product> successProducts = productService.countSuccessFailureBeforeSavingProducts(productList, "test.csv");

        assertAll(
                () -> assertEquals(2, successProducts.size()),
                () -> assertEquals(2, fileDetails.getSuccessCount()),
                () -> assertEquals(1, fileDetails.getFailureCount())
        );

        verify(fileDetailsRepo, times(3)).findByFilePath("test.csv");
        verify(fileDetailsRepo, times(3)).save(fileDetails);
        verify(productRepo, times(1)).findByCode("IPH-XR-BLK");
    }
}