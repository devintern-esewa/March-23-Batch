package com.multipledatabase;

import com.multipledatabase.inventorydb.dto.ProductDto;
import com.multipledatabase.inventorydb.entity.Product;
import com.multipledatabase.inventorydb.enums.ProductEnum;
import com.multipledatabase.inventorydb.exception.ProductAlreadyExistException;
import com.multipledatabase.inventorydb.exception.ProductDoesNotExistException;
import com.multipledatabase.inventorydb.repository.ProductRepository;
import com.multipledatabase.inventorydb.services.ProductServiceImpl;
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
    private ProductServiceImpl productService;

    public ProductServiceImplTest() {

        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void convertToProductEntity_WhenValidDtoIsPass_ThenReturnModel() {

        ProductDto productDto = new ProductDto("Laptop", "LHA", "Active", 4, 1000);
        Product product = new Product();
        product.setCode(productDto.getCode());
        product.setName(productDto.getName());
        assertEquals(product.getName(), productService.convertToProductEntity(productDto).getName());


    }

    @Test
    public void convertToProductDto_WhenValidProductIsPass_ThenReturnProductDto() {

        Product product = new Product();
        product.setName("Laptop");
        product.setCode("LHA44441");
        ProductDto productDto = new ProductDto();
        productDto.setName("Laptop");
        assertEquals(productDto.getName(), productService.convertToProductDto(Optional.of(product)).getName());
    }


    @Test
    public void findByName_WhenValidNameIsGiven_ThenReturnProductDto() {

        Product product = new Product();
        product.setName("Laptop");
        product.setCode("LHA44441");

        ProductDto productDto = new ProductDto("Laptop", "LHA44441", "Active", 4, 1000);
        when(productRepository.findByName("Laptop")).thenReturn(Optional.of(product));
        assertEquals(productDto.getName(), productService.findByName("Laptop").getName());

    }


    @Test
    public void findByName_WhenInvalidNameIsGiven_ThenThrowException() {

        when(productRepository.findByName("")).thenReturn(Optional.empty());
        assertThrows(ProductDoesNotExistException.class, () -> productService.findByName(""));
    }


    @Test
    public void addProduct_WhenValidProductIsAdded_ThenReturnProductDto() {


        ProductDto productDto = new ProductDto("Laptop", "LHA", "Active", 4, 1000);
        when(productRepository.findByName(productDto.getName())).thenReturn(Optional.empty());

        assertTrue(productService.addProduct(productDto));
    }


    @Test
    public void addProduct_WhenProductAlreadyExit_ThenThrowException() {

        Product product = new Product();
        product.setName("Laptop");
        product.setCode("LHA44441");
        ProductDto productDto = new ProductDto("Laptop", "LHA", "Active", 4, 1000);
        when(productRepository.findByName(productDto.getName())).thenReturn(Optional.of(product));
        assertThrows(ProductAlreadyExistException.class, () -> productService.addProduct(productDto));
    }

    @Test
    public void checkProductStatus_WhenProductExist_ThenReturnFalse() {

        List<ProductDto> productList = new ArrayList<>();
        ProductDto productDto = new ProductDto("Laptop", "LHA44441", "Active", 4, 1000);
        ProductDto productDto1 = new ProductDto("Mobile", "LHA", "Active", 4, 200);
        productList.add(productDto1);
        productList.add(productDto);
        Product product = new Product();
        product.setName("Laptop");
        product.setCode("LHA44441");
        product.setStatus(ProductEnum.ACTIVE);
        assertFalse(productService.checkProductStatus(product, productList));
    }

    @Test
    public void checkProductStatus_WhenProductExist_ThenReturnTrue() {

        List<ProductDto> productList = new ArrayList<>();
        ProductDto productDto1 = new ProductDto("Mobile", "LHA", "Active", 4, 200);
        productList.add(productDto1);
        Product product = new Product();
        product.setName("Laptop");
        product.setCode("LHA44441");
        product.setStatus(ProductEnum.ACTIVE);
        assertTrue(productService.checkProductStatus(product, productList));
    }


    @Test
    public void addAllProduct_WhenDataIsValid() {

        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setName("Laptop");
        product.setCode("LHA44441");
        productList.add(product);
        when(productRepository.saveAll(productList)).thenReturn(productList);
        assertTrue(productService.addAllProduct(productList));
    }

    @Test
    public void getAllProduct_ThenReturnProductDtoList() {
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setName("Laptop");
        product.setCode("LHA44441");
        productList.add(product);
        when(productRepository.findAll()).thenReturn(productList);
        assertEquals(product.getName(), productService.getAllProduct().get(0).getName());
    }

    @Test
    public void createProduct_ThenReturnProduct() {

        Product product = new Product();
        product.setName("Laptop");
        product.setCode("LHA4441");

        assertEquals(product.getName(), productService.createProduct("Laptop", "LHA4441", 5, 9).getName());

    }


}
