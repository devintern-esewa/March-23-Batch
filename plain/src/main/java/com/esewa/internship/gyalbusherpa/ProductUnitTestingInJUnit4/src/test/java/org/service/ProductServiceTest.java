package org.service;

import org.dao.ProductDao;
import org.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertNotNull;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testGetProductById() {
        Product product = new Product(1, "Product 1", 10.0);
        when(productDao.getProductById(1)).thenReturn(product);

        assertNotNull(productService.getProductById(1));
        assertEquals(product, productService.getProductById(1));
        assertEquals("Product 1", productService.getProductById(1).getName());
        assertEquals(10.0, productService.getProductById(1).getPrice(), 0.5);

        verify(productDao, times(4)).getProductById(1);
    }

    @Test
    public void testGetProductByIdNotFound() {
        when(productDao.getProductById(5)).thenReturn(null);
        assertThrows(RuntimeException.class, () -> productService.getProductById(5));
    }

    @Test
    public void testGetAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "Product 1", 10.0));
        productList.add(new Product(2, "Product 2", 20.0));
        productList.add(new Product(3, "Product 3", 30.0));

        when(productDao.getAllProducts()).thenReturn(productList);

        assertEquals(3, productService.getAllProducts().size());
        verify(productDao, times(1)).getAllProducts();
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product(1, "Product 1", 10.0);
        when(productDao.createProduct(product)).thenReturn(true);

        assertTrue(productService.createProduct(product));
        verify(productDao, times(1)).createProduct(product);
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product(1, "Update Product 1", 50.0);
        when(productDao.updateProduct(product)).thenReturn(true);

        assertTrue(productService.updateProduct(product));

        verify(productDao, times(1)).updateProduct(product);
    }

    @Test
    public void testUpdateProductNotFound() {
        Product product = new Product(5, "Update Product 5", 50.0);
        when(productDao.updateProduct(product)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> productService.updateProduct(product));
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product(3, "Product 3", 30.0);
        when(productDao.deleteProduct(product)).thenReturn(true);

        assertTrue(productService.deleteProduct(product));
    }

    @Test
    public void testDeleteProductNotFound() {
        Product product = new Product(5, "Product 5", 50.0);
        when(productDao.deleteProduct(product)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> productService.deleteProduct(product));
    }

    @Test
    public void testGetTotalPriceOfProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "Product 1", 10.0));
        productList.add(new Product(2, "Product 2", 20.0));
        productList.add(new Product(3, "Product 3", 30.0));

        when(productDao.getAllProducts()).thenReturn(productList);

        assertEquals(60.0, productService.getTotalPriceOfProducts(), 0.5);
    }
}