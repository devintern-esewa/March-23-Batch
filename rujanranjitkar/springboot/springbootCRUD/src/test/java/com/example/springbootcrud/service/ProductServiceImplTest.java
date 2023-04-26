package com.example.springbootcrud.service;

import com.example.springbootcrud.dto.ProductDto;
import com.example.springbootcrud.exception.EmptyFieldException;
import com.example.springbootcrud.exception.IdDoesNotExistsException;
import com.example.springbootcrud.exception.InvalidIdException;
import com.example.springbootcrud.model.Product;
import com.example.springbootcrud.repo.ProductRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
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

class ProductServiceImplTest {
    @Mock
    public ProductRepo productRepoMock;

    @Mock
    public ObjectMapper objectMapper;
    @InjectMocks
    public ProductServiceImpl productService;

    public ProductServiceImplTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Testing getAllProducts")
    public void getAllProducts_ShouldReturnProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "coke", 150.0));
        productList.add(new Product(2L, "pepsi", 140.0));

        when(productRepoMock.findAllProducts()).thenReturn(productList);
        assertEquals(productList, productService.getAllProducts());
        verify(productRepoMock, times(2)).findAllProducts();
    }

    @Test
    @DisplayName("Testing addNewProduct when there is no product")
    public void addNewProduct_ShouldThrowEmptyFieldException_WhenNoProduct() {
        ProductDto productDto = new ProductDto(1L, "", 0.0);
        assertThrows(EmptyFieldException.class, () -> productService.addNewProduct(productDto));
    }

    @Test
    @DisplayName("Testing addNewProduct when there is existing product")
    public void addNewProduct_ShouldReturnUpdatedProduct_WhenProductExists(){
        Product existingProduct=new Product(1L,"coke",150.0);
        ProductDto newProduct= new ProductDto(1L,"coke",150.0);
        when(productRepoMock.findProductByName("coke")).thenReturn(Optional.of(existingProduct));
        productService.addNewProduct(newProduct);
        assertEquals(300.0, existingProduct.getPrice());
        verify(productRepoMock,times(1)).save(existingProduct);
    }

//    @Test
//    @DisplayName("Testing addNewProduct when there is new product")
//    public void addNewProduct_ShouldReturnNewProduct_WhenProductExists(){
//        Product newProduct= new Product(1L,"coke",150.0);
//    }

    @Test
    @DisplayName("Testing getProductById when id is invalid")
    public void getProductById_ShouldThrowInvalidIdException_WhenInvalidId() {
        assertThrows(InvalidIdException.class, () -> productService.getProductById(-1L));
    }

    @Test
    @DisplayName("Testing getProductById when id does not exists")
    public void getProductById_ShouldThrowIdDoesNotExistsException_WhenIdDoesNotExists() {
        assertThrows(IdDoesNotExistsException.class, () -> productService.getProductById(5L));
    }

    @Test
    @DisplayName("Testing getProductById when id exists")
    public void getProductById_ShouldReturnProduct_WhenIdExists() {
        Product product = new Product(1L, "coke", 150.0);
        when(productRepoMock.findProductById(1L)).thenReturn(Optional.of(product));
        assertEquals(Optional.of(product), productService.getProductById(1L));
        verify(productRepoMock, times(1)).findProductById(1L);
    }

    @Test
    @DisplayName("Testing updateProduct when id is invalid")
    public void updateProduct_ShouldThrowInvalidIdException_WhenInvalidId() {
        ProductDto product = new ProductDto(0L, "coke", 150.0);
        assertThrows(InvalidIdException.class, () -> productService.updateProduct(product));
    }

    @Test
    @DisplayName("Testing updateProduct when id does not exists")
    public void updateProduct_ShouldThrowIdDoesNotExistsException_WhenIdDoesNotExists() {
        assertThrows(IdDoesNotExistsException.class,
                () -> productService.updateProduct(new ProductDto(5L, "coke", 150.0)));
    }
}