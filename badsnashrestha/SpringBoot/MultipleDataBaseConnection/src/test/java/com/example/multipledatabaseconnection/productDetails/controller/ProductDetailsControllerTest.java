package com.example.multipledatabaseconnection.productDetails.controller;

import com.example.multipledatabaseconnection.productDetails.dto.ProductDetailsResponseDto;
import com.example.multipledatabaseconnection.productDetails.enums.ProductStatus;
import com.example.multipledatabaseconnection.productDetails.model.ProductDetails;
import com.example.multipledatabaseconnection.productDetails.service.ProductDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductDetailsController.class)
class ProductDetailsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductDetailsService productDetailsService;


    @Test
    public void whenGetProductDetailsById_ThenReturnProductDetails() throws Exception {
        ProductDetailsResponseDto productDetailsResponseDto = new ProductDetailsResponseDto();
        productDetailsResponseDto.setProductName("Apple iPhone XR");
        productDetailsResponseDto.setProductStatus(ProductStatus.ACTIVE);
        productDetailsResponseDto.setCode("IPH-XR-BLK");
        productDetailsResponseDto.setQuantity(50);
        productDetailsResponseDto.setPrice(699.99);

        when(productDetailsService.getProductDetailsById(1L)).thenReturn(productDetailsResponseDto);

        mockMvc.perform(get("/productDetails/getProductDetailsById/1"))
                .andDo(print())
                .andExpect(jsonPath("$.code").value("IPH-XR-BLK"));


    }

    @Test
    public void whenDeleteProductDetailsById_ThenDeleteProductDetails() throws Exception {
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductName("Apple iPhone XR");
        productDetails.setProductStatus(ProductStatus.ACTIVE);
        productDetails.setCode("IPH-XR-BLK");
        productDetails.setQuantity(50);
        productDetails.setPrice(699.99);

        mockMvc.perform(delete("/productDetails/deleteProductById/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void whenGetAllProductDetails_ThenReturnFileDetailsList() throws Exception {
        List<ProductDetailsResponseDto> productDetailsResponseDtoList = new ArrayList<>();
        productDetailsResponseDtoList.add(new ProductDetailsResponseDto("Apple iPhone XR", ProductStatus.ACTIVE, "IPH-XR-BLK", 50, 699.99));
        productDetailsResponseDtoList.add(new ProductDetailsResponseDto("Apple iPhone X", ProductStatus.ACTIVE, "IPH-XR-BL", 20, 699.99));

        when(productDetailsService.getAllProductDetails(0)).thenReturn(productDetailsResponseDtoList);

        mockMvc.perform(get("/productDetails/getAllProductDetailsUsingPagination/0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[1].code").value("IPH-XR-BL"));

    }
}