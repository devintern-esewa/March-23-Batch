package com.product.service;

import com.product.dto.ProductDto;
import com.product.enums.ProductEnum;
import com.product.exception.ProductAlreadyExistException;
import com.product.model.Product;
import com.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void saveProduct_whenProductAlreadyExists_shouldThrowException() {

        ProductDto productDto = new ProductDto();
        productDto.setProductName("Test Product");
        productDto.setProductCode("TP01");
        productDto.setQuantity(10);
        productDto.setPrice(100.00);
        when(productRepository.findByProductName(anyString())).thenReturn(Optional.of(new Product()));

        assertThrows(ProductAlreadyExistException.class, () -> productService.saveProduct(productDto));
    }

    @Test
    void saveProduct_whenProductDoesNotExist_shouldSaveProduct() {

        ProductDto productDto = new ProductDto();
        productDto.setProductName("Test Product");
        productDto.setProductCode("TP001");
        productDto.setQuantity(10);
        productDto.setPrice(20.0);

        when(productRepository.findByProductName(productDto.getProductName())).thenReturn(Optional.empty());

        assertNotNull(productService.saveProduct(productDto));
        assertEquals(productDto.getProductName(), productService.saveProduct(productDto).getProductName());

        verify(productRepository, times(2)).findByProductName(productDto.getProductName());
        verify(productRepository, times(2)).save(any(Product.class));
    }

    @Test
    void getAllProducts_shouldReturnListOfProductDto() {
        List<ProductDto> productDtoList = new ArrayList<>();

        ProductDto productDto1 = new ProductDto();
        productDto1.setProductName("Test Product 1");
        productDto1.setProductCode("TP001");
        productDto1.setQuantity(10);
        productDto1.setPrice(20.0);

        productDtoList.add(productDto1);

        ProductDto productDto2 = new ProductDto();
        productDto2.setProductName("Test Product 2");
        productDto2.setProductCode("TP002");
        productDto2.setQuantity(20);
        productDto2.setPrice(30.0);

        productDtoList.add(productDto2);

        when(productRepository.getAllProductDto()).thenReturn(productDtoList);

        assertEquals(productDtoList.size(), productService.getAllProducts().size());
        assertEquals(productDto1.getProductName(), productService.getAllProducts().get(0).getProductName());
        assertEquals(productDto2.getProductName(), productService.getAllProducts().get(1).getProductName());
    }

    @Test
    public void testGetProductById() {
        long productId = 1L;
        ProductDto productDto = new ProductDto();
        productDto.setProductName("Product 1");
        productDto.setProductCode("P001");
        productDto.setQuantity(10);
        productDto.setPrice(100.0);

        when(productRepository.getProductDtoById(productId))
                .thenReturn(Optional.of(productDto));

        ProductDto fetchedProduct = productService.getProductById(productId);

        assertNotNull(fetchedProduct);
        assertEquals(productDto.getProductName(), fetchedProduct.getProductName());
        assertEquals(productDto.getProductCode(), fetchedProduct.getProductCode());
        assertEquals(productDto.getQuantity(), fetchedProduct.getQuantity());
        assertEquals(productDto.getPrice(), fetchedProduct.getPrice(), 0.0);
    }

    @Test
    public void testUpdateProduct() {
        long productId = 1L;
        ProductDto productDto = new ProductDto();
        productDto.setProductName("Product 1 Updated");
        productDto.setProductCode("P001U");
        productDto.setQuantity(20);
        productDto.setPrice(200.0);

        Product product = new Product();
        product.setId(productId);
        product.setProductName("Product 1");
        product.setProductCode("P001U");
        product.setProductStatus(ProductEnum.ACTIVE);
        product.setQuantity(20);
        product.setPrice(200.0);


        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // when
        ProductDto updatedProductDto = productService.updateProduct(productId, productDto);

        // then
        assertEquals(productDto.getProductName(), updatedProductDto.getProductName());
        assertEquals(productDto.getProductCode(), updatedProductDto.getProductCode());
        assertEquals(productDto.getQuantity(), updatedProductDto.getQuantity());
        assertEquals(productDto.getPrice(), updatedProductDto.getPrice());

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testDeleteProduct() {
        long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setProductName("Product 1");
        product.setProductCode("P001");
        product.setQuantity(10);
        product.setPrice(100.0);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        productService.deleteProduct(productId);

        verify(productRepository).findById(productId);
        verify(productRepository).delete(product);
    }

}