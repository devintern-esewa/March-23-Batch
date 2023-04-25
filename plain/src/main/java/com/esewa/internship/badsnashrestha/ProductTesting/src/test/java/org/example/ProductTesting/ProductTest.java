package org.example.ProductTesting;

import org.example.customException.InvalidIdException;
import org.example.dao.ProductDao;
import org.example.model.Product;

import org.example.service.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductTest {
    /*to create mock object of ProductDao
    should be done in interface

     */
    @Mock
    private ProductDao productDaoMock;
    /*to inject mock object to ProductServiceImpl
    should  be done  in class

     */
    @InjectMocks
    private ProductServiceImpl productServiceimpl;

    /* used to initialize the Mockito annotations in the test class. */
    public ProductTest() {
        MockitoAnnotations.initMocks(this);

    }

    @DisplayName("Test to get all students")
    @Test
    public void testGetAllStudents() {
        Product product = new Product(1, "Biscuit", 20.0f);
        Product product1 = new Product(2, "Wai Wai", 22.50f);
        Product product2 = new Product(3, "Coke", 150.0f);
        List<Product> productList = new ArrayList<Product>();
        productList.add(product);
        productList.add(product1);
        productList.add(product2);

        /*the behavior of the getAllProducts() method of the productDaoMock object
         returns the productList object when it is called during the unit testing
         */
        when(productDaoMock.getAllProducts()).thenReturn(productList);
        /*assert that the size of getAllProducts is equal to 3
        if  size doesn't match then test fails
         */
        assertEquals(3, productServiceimpl.getAllProducts().size());
        /*to verify that the method should be called exactly one time
        if time doesn't match then test fails
         */
        verify(productDaoMock, times(1)).getAllProducts();
    }

    @DisplayName("Test to get product by ID when given id doesn't exists")
    @Test
    public void testGetByIdWithIdDoesntExists() {
        assertThrows(InvalidIdException.class,
                () -> productServiceimpl.getById(0));
    }

    @DisplayName("Test to get product by ID when given id exists")
    @Test
    public void testGetByIdWithIdExists() {
        Product product = new Product(1, "SoyaBeans", 140.0f);
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productDaoMock.getProductById(1)).thenReturn(Optional.of(product));
        assertEquals(product, productServiceimpl.getById(1));
    }

    @DisplayName("Test to add new products with invalid data fields")
    @Test
    public void testAddNewProductWithInvalidData() {
        Product product = new Product(0, "", 0.0f);
        assertThrows(InvalidIdException.class,
                () -> productServiceimpl.addNewProduct(product));
//        assertEquals("Values are incorrect",productServiceimpl.addNewProduct(p));
    }

    @DisplayName("Test to add new product with valid data fields")
    @Test
    public void testAddNewProductWithValidData() {
        Product product = new Product(4, "SoyaBeans", 140.0f);
        when(productDaoMock.addNewProduct(product)).thenReturn(true);
        assertTrue(productServiceimpl.addNewProduct(product));
    }

    @DisplayName("Test to add new product where id already exists")
    @Test
    public void testAddNewProductWithIdAlreadyExist() {
        Product oldProduct = new Product(1, "SoyaBeans", 140.0f);
        List<Product> productList = new ArrayList<>();
        productList.add(oldProduct);

        Product newProduct = new Product(1, "Beans", 130.0f);
        when(productDaoMock.getProductById(1)).thenReturn(Optional.of(oldProduct));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> productServiceimpl.addNewProduct(newProduct));
        assertEquals("Product with already exists", exception.getMessage());

    }

    /*it will work for id that doesnt exists
     */
    @DisplayName("Test to delete product by id with invalid id field")
    @Test
    public void testDeleteByIdWithInvalidId() {
        assertThrows(InvalidIdException.class,
                () -> productServiceimpl.deleteById(-1));
    }

    @DisplayName("Test to delete product by id with id field that exists")
    @Test
    public void testDeleteByIdWithIdExists() {
        Product product = new Product(1, "Tomoto", 50.0f);
        when(productDaoMock.getProductById(1)).thenReturn(Optional.of(product));
        when(productDaoMock.deleteById(1)).thenReturn(true);
        assertTrue(productServiceimpl.deleteById(1));
        verify(productDaoMock, times(1)).deleteById(1);
    }

    @DisplayName("Test to update product with invalid id field")
    @Test
    public void testUpdateProductWithInvalidId() {
        assertThrows(InvalidIdException.class,
                () -> productServiceimpl.updateProduct(new Product(-1, "banana", 15.0f)));
    }

    @DisplayName("Test to update product with id which exists")
    @Test
    public void testUpdateProductWithIdExists() {
        Product product = new Product(3, "Apple", 230.0f);
        when(productDaoMock.getProductById(product.getProductId())).thenReturn(Optional.of(product));
        when(productDaoMock.updateProduct(product)).thenReturn(true);
        assertTrue(productServiceimpl.updateProduct(product));
    }


}
