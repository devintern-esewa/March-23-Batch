package com.example.mulltipledbconnectiontask.inventory.controller;

import com.example.mulltipledbconnectiontask.inventory.dto.ProductResponseDto;
import com.example.mulltipledbconnectiontask.inventory.enums.ProductStatus;
import com.example.mulltipledbconnectiontask.inventory.model.Product;
import com.example.mulltipledbconnectiontask.inventory.service.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productService;

    @Test
    public void getAllProductsTest() throws Exception {
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        productResponseDtoList.add(new ProductResponseDto("Apple iPhone XR", "IPH-XR-BLK", ProductStatus.ACTIVE, 50L, 699.99));
        productResponseDtoList.add(new ProductResponseDto("Apple iPhone X", "IPH-XR-BLU", ProductStatus.ACTIVE, 50L, 599.99));

        when(productService.getAllProducts(0)).thenReturn(productResponseDtoList);

        mockMvc.perform(get("/api/products/getAllProducts/0"))
                .andDo(print())
                .andExpect(jsonPath("$[1].code", is("IPH-XR-BLU")));
    }

    @Test
    public void getProductByIdTest() throws Exception {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setProductName("Apple iPhone XR");
        productResponseDto.setProductStatus(ProductStatus.ACTIVE);
        productResponseDto.setCode("IPH-XR-BLK");
        productResponseDto.setQuantity(50L);
        productResponseDto.setPrice(699.99);

        when(productService.getProductById(1L)).thenReturn(productResponseDto);

        mockMvc.perform(get("/api/products/getProductById/1"))
                .andDo(print())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.code", is("IPH-XR-BLK")));
    }

    @Test
    public void deleteProductByIdTest() throws Exception {
        Product product = new Product(1L, "Apple iPhone XR", "IPH-XR-BLK", ProductStatus.ACTIVE, 50L, 699.99);
        product.setProductStatus(ProductStatus.DELETED);
        mockMvc.perform(delete("/api/products/deleteProductById/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(product)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}