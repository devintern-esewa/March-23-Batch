package com.inventoryDb.controller;

import com.inventoryDb.dto.ProductDto;
import com.inventoryDb.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void getProductById() throws Exception {
        List<ProductDto> productDtos = new ArrayList<>();
        ProductDto product1 = ProductDto.builder()
                .name("p1")
                .code("p")
                .price(1)
                .build();

        productDtos.add(product1);
        when(productService.getProductById(1)).thenReturn(productDtos);

        mockMvc.perform(
                get("/products/1")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$[0].name",is("p1")));
    }

    @Test
    void getAllProducts() throws Exception {
        List<ProductDto> productDtos = Arrays.asList(
                ProductDto.builder()
                        .name("p2")
                        .code("Pd")
                        .price(10)
                        .build(),
                ProductDto.builder()
                        .name("p2")
                        .code("Pd")
                        .price(102)
                        .build(),
                ProductDto.builder()
                        .name("p3")
                        .code("ag")
                        .price(1003)
                        .build()
        );

        when(productService.getAllProducts()).thenReturn(productDtos);

        mockMvc.perform(
                        get("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[2].name", is("p3")));
    }

}