package com.example.multipledatabaseconnection.productDetails.service;

import com.example.multipledatabaseconnection.fileDetails.enums.FileStatus;
import com.example.multipledatabaseconnection.fileDetails.model.FileDetails;
import com.example.multipledatabaseconnection.fileDetails.repo.FileDetailsRepo;
import com.example.multipledatabaseconnection.productDetails.enums.ProductStatus;
import com.example.multipledatabaseconnection.productDetails.model.ProductDetails;
import com.example.multipledatabaseconnection.productDetails.repo.ProductDetailsRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductDetailsServiceImplTest {
    @Mock
    private FileDetailsRepo fileDetailsRepomock;
    @Mock
    private ProductDetailsRepo productDetailsRepo;

    @InjectMocks
    private ProductDetailsServiceImpl productDetailsServiceImpl;

    @Test
    public void whenReadCsvInsertIntoProductDetails_ThenReturnProductList() throws IOException {

        String filePath = "src/test/resources/product2.csv";
        List<ProductDetails> productDetailsList = productDetailsServiceImpl.readCsvInsertIntoProductDetails(filePath);

        assertEquals(11, productDetailsList.size());
        assertAll(
                () -> assertEquals(1L, productDetailsList.get(0).getProductId()),
                () -> assertEquals("Apple iPhone XR", productDetailsList.get(0).getProductName()),
                () -> assertEquals("IPH-XR-BLK", productDetailsList.get(0).getCode()),
                () -> assertEquals(ProductStatus.ACTIVE, productDetailsList.get(0).getProductStatus()),
                () -> assertEquals(50, productDetailsList.get(0).getQuantity()),
                () -> assertEquals(699.99, productDetailsList.get(0).getPrice())
        );

    }

    @Test
    public void whenProcessingProduct_ThenThrowSuccessProductDetails() {
        List<ProductDetails> productDetails = new ArrayList<>();
        productDetails.add(new ProductDetails(1L, "Apple iPhone XR", ProductStatus.ACTIVE, "IPH-XR-BLK", 50, 699.99));
        productDetails.add(new ProductDetails(2L, "HP Spectre x360", ProductStatus.ACTIVE, "HP-SPX-360", 10, 1499.99));

        FileDetails fileDetails = new FileDetails();
        fileDetails.setFileDetailsId(1L);
        fileDetails.setFilePath("test.csv");
        fileDetails.setFileStatus(FileStatus.PENDING);
        fileDetails.setSuccessCount(0);
        fileDetails.setFailureCount(0);

        when(fileDetailsRepomock.findByFilePath(eq("test.csv"))).thenReturn(fileDetails);
        when(productDetailsRepo.getByCode("IPH-XR-BLK")).thenReturn(productDetails.get(0));

        List<ProductDetails> productDetailsList = productDetailsServiceImpl.processingProduct(productDetails, fileDetails.getFilePath());
        assertAll(
                () -> assertEquals(1, productDetailsList.size()),
                () -> assertEquals(1, fileDetails.getSuccessCount()),
                () -> assertEquals(1, fileDetails.getFailureCount())
        );

        verify(fileDetailsRepomock, times(2)).findByFilePath(fileDetails.getFilePath());
        verify(fileDetailsRepomock, times(2)).save(fileDetails);
        verify(productDetailsRepo, times(1)).getByCode("IPH-XR-BLK");
    }


}