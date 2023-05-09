package com.example.nodbtask.service.impl;

import com.example.nodbtask.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ProductServiceImpl.class})
public class ProductServiceImplTest {
    private ProductServiceImpl productServiceImpl;
    private static Map<Long, Product> productMap;

    @Before
    public void setUp() {
        productServiceImpl = new ProductServiceImpl();

        MockitoAnnotations.initMocks(this);
        // Mock the static productMap
        productMap = new HashMap<>();
        productMap.put(1L, new Product(1L, "Laptop", "LP1", 120000L, 12L));
        productMap.put(2L, new Product(2L, "Computer", "Cm4", 11000L, 8L));
    }
    @Test
    //@DisplayName("getAllProductTestingTrueCondition")
    public void getAllProduct_TestGetAllProduct_True() {
        // Mocking the static method
        PowerMockito.mockStatic(ProductServiceImpl.class);
        PowerMockito.when(ProductServiceImpl.getAllProduct())
                .thenReturn(new ArrayList<>(productMap.values()));

        // Act
        List<Product> productList = productServiceImpl.getAllProduct();

        // Assert
        assertEquals(2, productList.size());
        Assert.assertTrue(productList.contains(productMap.get(1L)));
        Assert.assertTrue(productList.contains(productMap.get(2L)));

        PowerMockito.verifyStatic(ProductServiceImpl.class);
        ProductServiceImpl.getAllProduct();
    }

    @Test
   // @DisplayName("findByIdTestingTrueCondition")
    public void findById_findProductUsingId_True() {
        // Arrange
        Long productId = 1L;
        Product expectedProduct = productMap.get(productId);

        // Act
        Product actualProduct = productServiceImpl.findById(productId);

        // Assert
        assertEquals( productId, actualProduct.getId());
        assertEquals(expectedProduct.getName(), actualProduct.getName());
        assertEquals(expectedProduct.getCode(), actualProduct.getCode());
        assertEquals(expectedProduct.getPrice(), actualProduct.getPrice());
        assertEquals(expectedProduct.getQuantity(), actualProduct.getQuantity());
    }

    @Test
   // @DisplayName("addProductTestingTrue")
    public void addProduct_TestAddProduct_True() {
        // Arrange
        Product product = mock(Product.class);

        // Act
        Product addedProduct = productServiceImpl.addProduct(product);

        // Assert
        assertEquals(product, addedProduct);
        assertEquals(3, ProductServiceImpl.getAllProduct().size());
    }

    @Test
   // @DisplayName("UpdateProductTestTrue")
    public void updateProduct_TestUpdateProduct_True() {
        // Arrange
        Product productToUpdate = new Product(1L, "Laptop", "LP1", 120000L, 12L);

        //Act
        Product updatedProduct = productServiceImpl.updateProduct(1L, productToUpdate);

        //Assert
        assertEquals( Long.valueOf(1L), updatedProduct.getId());
        assertEquals( "Laptop", updatedProduct.getName());
        assertEquals("LP1", updatedProduct.getCode());
        assertEquals( Long.valueOf(120000L), updatedProduct.getPrice());
        assertEquals( Long.valueOf(12L), updatedProduct.getQuantity());
    }

    @Test
    @DisplayName("DeleteProductTrue")
    public void deleteProduct_TestDeleteProduct_True() {
        // Arrange
        Product product1 = new Product(1L, "Laptop", "LP1", 120000L, 12L);
        productServiceImpl.addProduct(product1);

        // Act
        Product deletedProduct = productServiceImpl.deleteProduct(1);

        // Assert
//        assertNotNull(deletedProduct);
        assertEquals(Long.valueOf(1L), deletedProduct.getId());
//        assertNull(productServiceImpl.findById(1L));
    }
}