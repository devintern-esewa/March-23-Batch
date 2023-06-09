package com.restapi.product;

import com.restapi.product.customexception.DataProvidedInvalidException;
import com.restapi.product.customexception.ProductAlreadyExistException;
import com.restapi.product.customexception.ProductDoesNotExistException;
import com.restapi.product.dao.ProductRepository;
import com.restapi.product.dto.ProductDto;
import com.restapi.product.enums.ProductEnum;
import com.restapi.product.model.Product;
import com.restapi.product.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {


    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;


    public ProductServiceImplTest() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getAllProducts_ThenReturnProducts() {

        List<Product> productList = new ArrayList<>();
        Product p1 = new Product(1, "Laptop", ProductEnum.Active, 9002022.0, "Electronics", "Best");
        Product p2 = new Product(2, "Car", ProductEnum.Active, 9999.0, "Vehicles", "Good");
        productList.add(p1);
        productList.add(p2);
        when(productRepository.findAll()).thenReturn(productList);
        assertEquals(productList.get(0).getProductName(), productServiceImpl.getAllProducts().get(0).getProductName());
    }

    @Test
    public void getProduct_WhenProductNameIsValid_ThenReturnProduct() {
        Product p2 = new Product(5, "Laptop", ProductEnum.Active, 9009999.0, "Electronics", "Best");
        when(productRepository.findByProductName("Laptop")).thenReturn(Optional.of(p2));
        assertEquals(p2.getProductName(), productServiceImpl.getProduct("Laptop").getProductName());

    }

    @Test
    public void getProduct_WhenProductNameIsNotValid_ThenThrowException() {

        when(productRepository.findById(6)).thenReturn(Optional.empty());
        assertThrows(ProductDoesNotExistException.class
                , () -> productServiceImpl.getProduct("Laptop"));
    }


    @Test
    public void addProduct_WhenAllDataIsValid_ThenReturnProduct() {
        ProductDto productDto = new ProductDto( "Laptop", 8000.0, "Electronics", "Best");
        when(productRepository.findById(1)).thenReturn(Optional.empty());
        assertEquals(productDto.getProductName(), productServiceImpl.addProduct(productDto).getProductName());
    }

    @Test
    public void addProduct_WhenProductAlreadyExist_ThenThrowException() {
        Product p1 = new Product(1, "Laptop", ProductEnum.Active, 8000.0, "Electronics", "Best");
        ProductDto productDto = new ProductDto( "Laptop", 8000.0, "Electronics", "Best");
        when(productRepository.findByProductName("Laptop")).thenReturn(Optional.of(p1));
        assertThrows(ProductAlreadyExistException.class, () -> productServiceImpl.addProduct(productDto));
    }

    @Test
    public void addProduct_WhenDataProvidedIsInvalid_ThenReturnException() {
        ProductDto productDto = new ProductDto("Laptop", -8000.0, "Electronics", "Best");
        assertThrows(DataProvidedInvalidException.class
                , () -> productServiceImpl.addProduct(productDto));
    }


    @Test
    public void updateProduct_WhenProvidedIdExist_ThenReturnProduct() {
        Product p1 = new Product(1, "Laptop", ProductEnum.Active, 8000.0, "Electronics", "Best");
        ProductDto productDto = new ProductDto("Laptop", 8000.0, "Electronics", "Best");
        when(productRepository.findByProductName("Laptop")).thenReturn(Optional.of(p1));
        assertEquals(productDto.getProductName(), productServiceImpl.updateProduct("Laptop", productDto).getProductName());
    }

    @Test
    public void updateProduct_WhenProvidedIdDoesNotExist_ThenThrowException() {
        Product p1 = new Product(1, "Laptop", ProductEnum.Active, 8000.0, "Electronics", "Best");
        ProductDto productDto = new ProductDto( "Laptop", 8000.0, "Electronics", "Best");
        when(productRepository.findById(10)).thenReturn(Optional.empty());
        assertThrows(ProductDoesNotExistException.class, () -> productServiceImpl.updateProduct("Laptop", productDto));
    }


    @Test
    public void deleteProduct_WhenProvidedIdExist() {

        Product p1 = new Product(1, "Laptop", ProductEnum.Active, 8000.0, "Electronics", "Best");
        when(productRepository.findByProductName("Laptop")).thenReturn(Optional.of(p1));
        assertTrue(productServiceImpl.deleteProduct("Laptop"));
    }

    @Test
    public void deleteProduct_WhenProvidedIdDoesNotExist_ThenThrowException() {

        when(productRepository.findByProductName("Laptop")).thenReturn(Optional.empty());

        assertThrows(ProductDoesNotExistException.class, () -> productServiceImpl.deleteProduct("Laptop"));
    }



}
