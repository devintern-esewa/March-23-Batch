package org.example.ProductTesting;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductTest {
    //to create mock object of ProductDao
    //should be done in interface
    @Mock
   private ProductDao productDaoMock;
    //to inject mock object to ProductServiceImpl
    //should  be done  in class
    @InjectMocks
  private ProductServiceImpl productServiceimpl;

   // used to initialize the Mockito annotations in the test class.
    public ProductTest() {
        MockitoAnnotations.openMocks(this);

    }

    @DisplayName("Test to get all students")
    @Test
    public void testgetAllStudents(){
        Product p=new Product(1,"Biscuit",20.0f);
        Product p1=new Product(2,"Wai Wai",22.50f);
        Product p2=new Product(3,"Coke",150.0f);
        List<Product> productList=new ArrayList<Product>();
        productList.add(p);
        productList.add(p1);
        productList.add(p2);

        //the behavior of the getAllProducts() method of the productDaoMock object
        // returns the productList object when it is called during the unit testing
        when(productDaoMock.getAllProducts()).thenReturn(productList);
        //assert that the size of getAllProducts is equal to 3
        //if  size doesn't match then test fails
        assertEquals(3,productServiceimpl.getAllProducts().size());
        //to verify that the method should be called exactly one time
        //if time doesn't match then test fails
        verify(productDaoMock,times(1)).getAllProducts();
    }

    @DisplayName("Test to add new products with empty data fields")
    @Test
    public void testaddNewProductWithEmptyData(){
        Product p=new Product(0,"",0.0f);
        assertThrows(RuntimeException.class,
                ()->productServiceimpl.addNewProduct(p));
//        assertEquals("Values are incorrect",productServiceimpl.addNewProduct(p));
    }

    @DisplayName("Test to add new product with valid data fields")
    @Test
    public void testaddNewProductWithValidData(){
        Product p=new Product(4,"SoyaBeans",140.0f);
        when(productDaoMock.addNewProduct(p)).thenReturn(true);
        assertTrue(productServiceimpl.addNewProduct(p));
    }

    @DisplayName("Test to delete product by id with invalid id field")
    @Test
    public void testdeleteByIdWithInvalidId(){
        assertThrows(RuntimeException.class,
                ()->productServiceimpl.deleteById(0));
    }

    @DisplayName("Test to delete product by id with id field that exists")

    @Test
    public void testdeleteByIdWithIdExists(){
        Product p=new Product(5,"Tomoto",50.0f);
        when(productDaoMock.getProductById(5)).thenReturn(p);
        when(productDaoMock.deleteById(5)).thenReturn(true);
        assertTrue(productServiceimpl.deleteById(5));
        verify(productDaoMock,times(1)).deleteById(5);
    }

    @DisplayName("Test to delete product by id which doesnt exists ")
    @Test
    public void testdeleteByIdWithIdDoesntExists(){
    Product p=new Product(6,"sun cream",50.0f);
    when(productDaoMock.getProductById(6)).thenReturn(null);
    when(productDaoMock.deleteById(6)).thenReturn(false);
    assertFalse(productServiceimpl.deleteById(6));
    }

    @DisplayName("Test to update product with invalid id field")
    @Test
    public void testupdateProductWithInvalidId(){
        assertThrows(RuntimeException.class,
                ()->productServiceimpl.updateProduct(-1));
    }

    @DisplayName("Test to update product with id which exists")
    @Test
    public void testupdateProductWithIdExists(){
        Product p=new Product(3,"Apple",230.0f);
        when(productDaoMock.getProductById(3)).thenReturn(p);
        when(productDaoMock.updateProduct(3)).thenReturn(true);
        assertTrue(productServiceimpl.updateProduct(3));
    }

    @DisplayName("Test to update product with id that doesnt exists")
    @Test
    public void testupdateProductWithIdDoesntExists(){
        Product p=new Product(10,"Chocolate",10.25f);
        when(productDaoMock.getProductById(10)).thenReturn(null);
        assertFalse(productServiceimpl.updateProduct(10));
    }
}
