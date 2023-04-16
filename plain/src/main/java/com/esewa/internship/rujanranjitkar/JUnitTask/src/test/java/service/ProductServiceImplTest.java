package service;

import dao.ProductDAO;
import model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@DisplayName("Testing of product")
public class ProductServiceImplTest {
    @Mock
    private ProductDAO productDAOMock;
    @InjectMocks
    private ProductServiceImpl productService;

    public ProductServiceImplTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Testing getAllProducts ")
    public void getAllProductsTest() {
        List<Product> productList = new ArrayList<>();
        Product p1 = new Product(1, "Iphone", 100000.0);
        Product p2 = new Product(2, "Samsung", 90000.0);
        productList.add(p1);
        productList.add(p2);
        when(productDAOMock.getAllProducts()).thenReturn(productList);
        assertEquals(productList, productService.getAllProducts());
    }

    @Test
    @DisplayName("Testing getProductById with existing id")
    public void getProductByIdTest_WhenIdExists() {
        List<Product> productList = new ArrayList<>();
        Product p1 = new Product(1, "Iphone", 100000.0);
        Product p2 = new Product(2, "Samsung", 90000.0);
        productList.add(p1);
        productList.add(p2);
        when(productDAOMock.getProductById(anyInt())).thenReturn(productList.get(anyInt()));
        assertEquals(productList.get(0), productService.getProductById(1));
    }

    @Test
    @DisplayName("Testing getProductById with invalid id")
    public void getProductByIdTest_ShouldThrowException_WhenInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> productService.getProductById(-1));
    }

    @Test
    @DisplayName("Testing getProductById with non-existing id")
    public void getProductBtIdTest_WhenIdDoesNotExist() {
        when(productDAOMock.getProductById(5)).thenReturn(null);
        assertNull(productService.getProductById(5));
    }

    @Test
    @DisplayName("Testing addProduct with invalid id")
    public void addProductTest_ShouldThrowException_WhenInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> productService.getProductById(-1));
    }

    @Test
    @DisplayName("Testing addProduct with same id")
    public void addProductTest_WhenSameId() {
        Product existingProduct = new Product(1, "Iphone", 100000.0);
        Product newProduct = new Product(1, "Iphone", 100000.0);
        //Mocking getProductBtId method of mock object to return the existing product
        when(productDAOMock.getProductById(1)).thenReturn(existingProduct);
        // Calling the addProduct method of the ProductServiceImpl object with the newProduct
        // and verifying that it throws an IllegalArgumentException with the expected error message
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.addProduct(newProduct));
        assertEquals("Id already exists", exception.getMessage());
        //verifying if the getProductById method of mock object is called with expected parameter
        verify(productDAOMock, times(1)).getProductById(1);
    }

    @Test
    @DisplayName("Testing addProduct with valid data")
    public void addProductTest_WhenValidData() {
        Product newProduct = new Product(4, "Oppo", 20000.0);
        doNothing().when(productDAOMock).addProduct(newProduct);
    }

    @Test
    @DisplayName("Testing updateProduct")
    public void updateProductTest() {
        // Creating a product with id 1 and adding it to ProductDAO
        Product existingProduct = new Product(1, "Iphone", 100000.0);
        productDAOMock.addProduct(existingProduct);
        Product updatedProduct = new Product(1, "Iphone", 110000.0);
        // Calling the updateProduct method of ProductServiceImpl with the updated product
        productService.updateProduct(updatedProduct);
        // Mocking the getProductById method of ProductDAO to return the updated product
        when(productDAOMock.getProductById(1)).thenReturn(updatedProduct);
        // Verifying that the updateProduct method of ProductDao was called once with the updated product
        verify(productDAOMock, times(1)).updateProduct(updatedProduct);
        // Verifying that the product with ID 1 was updated with the new values
        assertEquals(updatedProduct, productService.getProductById(1));
    }

    @Test
    @DisplayName("Testing deleteProduct with invalid id")
    public void deleteProductTest_ShouldThrowException_WhenInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> productService.deleteProduct(-1));
    }

    @Test
    @DisplayName("Testing deleteProduct")
    public void deleteProductTest() {
        Product existingProduct = new Product(1, "Iphone", 100000.0);
        productDAOMock.addProduct(existingProduct);
        productService.deleteProduct(1);
        // Verifying that the deleteProduct method of ProductDao was called once with id 1
        verify(productDAOMock, times(1)).deleteProduct(1);
        assertNull(productService.getProductById(1));
    }
}
