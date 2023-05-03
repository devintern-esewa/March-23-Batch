package com.restapi.product;

import com.restapi.product.customexception.DataProvidedInvalidException;
import com.restapi.product.customexception.IdAlreadyExistException;
import com.restapi.product.customexception.IdDoesNotExistException;
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
    public void getAllProductsThenReturnProducts() {

        List<Product> productList = new ArrayList<>();
        Product p1 = new Product(1, "Laptop", ProductEnum.Active, 9002022.0, "Electronics", "Best");
        Product p2 = new Product(2, "Car", ProductEnum.Active, 9999.0, "Vehicles", "Good");
        productList.add(p1);
        productList.add(p2);
        when(productRepository.findAll()).thenReturn(productList);
        assertEquals(productList.get(0).getProductName(), productServiceImpl.getAllProducts().get(0).getProductName());
    }

    @Test
    public void getProductWhenIdIsValidThenReturnProduct() {
        Product p2 = new Product(5, "Laptop", ProductEnum.Active, 9009999.0, "Electronics", "Best");
        when(productRepository.findById(5)).thenReturn(Optional.of(p2));
        assertEquals(p2.getProductName(), productServiceImpl.getProduct(5).getProductName());

    }

    @Test
    public void getProductWhenIdIsNotValidThenThrowException() {

        when(productRepository.findById(6)).thenReturn(Optional.empty());
        assertThrows(IdDoesNotExistException.class
                , () -> productServiceImpl.getProduct(6));
    }


    @Test
    public void addProductWhenAllDataIsValidThenReturnProduct() {
        ProductDto productDto = new ProductDto("Laptop", 8000.0, "Electronics", "Best");
        when(productRepository.findById(1)).thenReturn(Optional.empty());
        assertEquals(productDto.getProductName(), productServiceImpl.addProduct(productDto).getProductId());
    }

    @Test
    public void addProductWhenIdAlreadyExistThenThrowException() {
        Product p1 = new Product("Laptop", ProductEnum.Active, 8000.0, "Electronics", "Best");
        ProductDto productDto = new ProductDto("Laptop", 8000.0, "Electronics", "Best");
        when(productRepository.findById(1)).thenReturn(Optional.of(p1));
        assertThrows(IdAlreadyExistException.class, () -> productServiceImpl.addProduct(productDto));
    }

    @Test
    public void addProductWhenDataProvidedIsInvalidThenReturnException() {
        ProductDto productDto = new ProductDto("Laptop", -8000.0, "Electronics", "Best");
        assertThrows(DataProvidedInvalidException.class
                , () -> productServiceImpl.addProduct(productDto));
    }


    @Test
    public void updateProductWhenProvidedIdExistThenReturnProduct() {
        Product p1 = new Product(1, "Laptop", ProductEnum.Active, 8000.0, "Electronics", "Best");
        ProductDto productDto = new ProductDto(1, "Laptop", 8000.0, "Electronics", "Best");
        when(productRepository.findById(1)).thenReturn(Optional.of(p1));
        assertEquals(productDto.getProductId(), productServiceImpl.updateProduct(1, productDto).getProductId());
    }

    @Test
    public void updateProductWhenProvidedIdDoesNotExistThenThrowException() {
        Product p1 = new Product(1, "Laptop", ProductEnum.Active, 8000.0, "Electronics", "Best");
        ProductDto productDto = new ProductDto(1, "Laptop", 8000.0, "Electronics", "Best");
        when(productRepository.findById(10)).thenReturn(Optional.empty());
        assertThrows(ProductDoesNotExistException.class, () -> productServiceImpl.updateProduct(10, productDto));
    }


    @Test
    public void deleteProductWhenProvidedIdExist() {

        Product p1 = new Product(1, "Laptop", ProductEnum.Active, 8000.0, "Electronics", "Best");
        when(productRepository.findById(1)).thenReturn(Optional.of(p1));
        assertTrue(productServiceImpl.deleteProduct(1));
    }

    @Test
    public void deleteProductWhenProvidedIdDoesNotExistThenThrowException() {

        when(productRepository.findById(100)).thenReturn(Optional.empty());

        assertThrows(ProductDoesNotExistException.class, () -> productServiceImpl.deleteProduct(100));
    }
}
