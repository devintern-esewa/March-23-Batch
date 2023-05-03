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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void testProcessProduct() {
        // Prepare test data
        List<Product> inputProducts = Arrays.asList(
                new Product(1,"p1", "c1", ProductEnum.ACTIVE, 1.0, 10.0),
                new Product(2,"p2", "c2", ProductEnum.ACTIVE, 2.0, 20.0),
                new Product(3,"p3", "c3", ProductEnum.ACTIVE, 3.0, 30.0),
                new Product(4,"p4", "c4", ProductEnum.ACTIVE, 4.0, 40.0)
        );

        FileDetail fileDetail = new FileDetail();
        fileDetail.setId(1L);
        fileDetail.setFilePath("test.csv");
        fileDetail.setSuccessCount(0);
        fileDetail.setFailureCount(0);

        when(fileDetailRepository.findFileDetailByFilePath(eq("test.csv"))).thenReturn(fileDetail);

        when(productRepository.getProductByProductStatusAndCode(eq(ProductEnum.ACTIVE), eq("c1")))
                .thenReturn(Optional.empty());

        // Call the method being tested
        List<Product> outputProducts = productService.processProduct(inputProducts, "test.csv");

        // Verify the results
        assertEquals(4, outputProducts.size());
        assertEquals(4, fileDetail.getSuccessCount());
        assertEquals(0, fileDetail.getFailureCount());

//        // Verify repository interactions
        verify(productRepository, times(4)).getProductByProductStatusAndCode(eq(ProductEnum.ACTIVE), anyString());
        verify(fileDetailRepository, times(4)).save(any(FileDetail.class));
    }
}