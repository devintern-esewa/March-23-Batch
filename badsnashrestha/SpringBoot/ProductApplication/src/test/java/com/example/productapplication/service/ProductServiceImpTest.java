package com.example.productapplication.service;

import com.example.productapplication.customException.EmptyFieldException;
import com.example.productapplication.customException.IdDoesntExistsException;
import com.example.productapplication.customException.InvalidIdException;
import com.example.productapplication.dto.ProductDto;
import com.example.productapplication.model.Product;
import com.example.productapplication.repo.ProductRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProductServiceImpTest {
    @Mock
    public ProductRepo productRepoMock;
    @Mock
    public ObjectMapper objectMapper;

    @InjectMocks
    public ProductServiceImpl productService;

    public ProductServiceImpTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllProductTest(){
        List<Product> productList=new ArrayList<>();
        productList.add(new Product(1,"Coke",160.0));
        productList.add(new Product(2,"Fanta",150.0));
        productList.add(new Product(3,"Sprite",140.0));
        when(productRepoMock.findAllProduct()).thenReturn(productList);
        assertEquals(productList,productService.getAllProducts());
        //2 because in service layer findAllProducts has been called 2 times
        verify(productRepoMock,times(2)).findAllProduct();
    }

    @Test
    public void addNewProduct_WhenEmptyFields_ThenThrowEmptyFieldException(){
        ProductDto productDto=new ProductDto(1,"",0.0);
        assertThrows(EmptyFieldException.class,
                ()->productService.addNewProduct(productDto));
    }

    @Test
    public void addNewProduct_WhenProductAlreadyExists_ThenUpdatePriceOfProduct(){
        Product existingProduct=new Product(1,"Coke",100.0);
        ProductDto productDto =new ProductDto(1,"Coke",15.0);
        when(productRepoMock.findProductByName("Coke")).thenReturn(Optional.of(existingProduct));
        productService.addNewProduct(productDto);
        assertEquals(115.0,existingProduct.getPrice());
        verify(productRepoMock,times(1)).save(existingProduct);

    }

    @Test
    public void addNewProduct_WhenProductDoesntAlreadyExists_ThenReturnNewProduct(){
        ProductDto productDto =new ProductDto(1,"Coke",15.0);
        when(productRepoMock.findProductByName("Coke")).thenReturn(Optional.empty());
        productService.addNewProduct(productDto);
        verify(productRepoMock,times(1)).saveNewProduct(productDto.getProductName(),productDto.getPrice());
    }

    @Test
    public void getProductById_WhenInvalidId_ThenThrowInvalidIdException(){
        assertThrows(InvalidIdException.class,
                () -> productService.getProductById(-1));
    }
    @Test
    public void getProductById_WhenIdDoesntExists_ThenReturnIdDoesntExistsException(){
        assertThrows(IdDoesntExistsException.class,
                ()->productService.getProductById(5));
    }

    @Test
    public void getProductById_WhenIdExists_ThenReturnProduct(){
        Product product=new Product(1, "Coke", 160.0);
        when(productRepoMock.findProductById(1)).thenReturn(Optional.of(product));
        assertEquals(Optional.of(product),productService.getProductById(1));
       verify(productRepoMock,times(2)).findProductById(1);
    }



}
