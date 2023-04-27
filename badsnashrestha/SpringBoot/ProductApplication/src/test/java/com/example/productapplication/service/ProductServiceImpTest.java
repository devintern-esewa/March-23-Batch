package com.example.productapplication.service;

import com.example.productapplication.customException.EmptyFieldException;
import com.example.productapplication.customException.IdDoesntExistsException;
import com.example.productapplication.customException.InvalidIdException;
import com.example.productapplication.dto.ProductDto;
import com.example.productapplication.model.Product;
import com.example.productapplication.repo.ProductRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        ProductDto product=new ProductDto(productDto.getProductId(),productDto.getProductName(),productDto.getPrice());
        when(productRepoMock.findProductByName("Coke")).thenReturn(Optional.empty());
        when(objectMapper.convertValue(productDto, ProductDto.class)).thenReturn(product);
        productService.addNewProduct(productDto);
        verify(productRepoMock,times(1)).saveNewProduct(productDto.getProductName(),productDto.getPrice());
    }

    //error while  testing
    @Test
    public void addNewProductList_WhenListOfProductIsToBeAdded_ThenReturnAddListOfProducts(){
        List<ProductDto> productList=new ArrayList<>();
        productList.add(new ProductDto(1,"Coke",160.0));
        productList.add(new ProductDto(2,"Fanta",150.0));

        Product product=new Product(1, "Coke", 160.0);
        Product product1=new Product(2, "Fanta", 150.0);

        productService.addNewProductList(productList);
        verify(productRepoMock,times(1)).save(product);
        verify(productRepoMock,times(1)).save(product1);

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
       verify(productRepoMock,times(1)).findProductById(1);
    }

    @Test
    public void deleteProductById_WhenIdInvalid_ThenReturnInvalidIdException(){
        assertThrows(InvalidIdException.class,
                ()->productService.deleteProductById(-1));
    }

    @Test
    public void deleteProductById_WhenIdDoesntExists_ThenReturnIdDoesntExistsException(){
        when(productRepoMock.findProductById(5)).thenReturn(Optional.empty());
        assertThrows(IdDoesntExistsException.class,
                ()->productService.deleteProductById(5));
    }

    @Test
    public void deleteProductById_WhenIdExists_ThenReturnDeleteProduct(){
        Product product=new Product(1,"Coke",150.0);
        when(productRepoMock.findProductById(1)).thenReturn(Optional.of(product));
        productService.deleteProductById(1);
        verify(productRepoMock,times(1)).deleteProductById(1);
    }

    @Test
    public void updateProduct_WhenIdInvalid_ThenReturnInvalidIdException(){
        ProductDto productDto=new ProductDto(-1,"Coke",150.0);
        assertThrows(InvalidIdException.class,
                ()->productService.updateProduct(productDto));
    }

    @Test
    public void updateProduct_WhenIdDoesntExists_ThenReturnIdDoesntException(){
        ProductDto productDto=new ProductDto(5,"Coke",150.0);
        when(productRepoMock.findProductById(productDto.getProductId())).thenReturn(Optional.empty());
        assertThrows(IdDoesntExistsException.class,
                ()->productService.updateProduct(productDto));
    }

    @Test
    public void updateProduct_WhenProductExists_ThenReturnUpdateProduct(){
        Product existingProduct=new Product(5,"Coke",150.0);
        ProductDto newProduct=new ProductDto(5,"Cokkee",120.0);

        when(productRepoMock.findProductById(newProduct.getProductId())).thenReturn(Optional.of(existingProduct));
        productService.updateProduct(newProduct);
        assertEquals(120.0,newProduct.getPrice());
    }

}
