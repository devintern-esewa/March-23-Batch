package com.multipledatabase;

import com.multipledatabase.inventorydb.dto.ProductDto;
import com.multipledatabase.inventorydb.entity.Product;
import com.multipledatabase.inventorydb.enums.ProductEnum;
import com.multipledatabase.inventorydb.repository.ProductRepository;
import com.multipledatabase.inventorydb.services.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class ProductServiceImplTest {


    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    public ProductServiceImplTest(){

        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void convertToModel_WhenValidDtoIsPass_ThenReturnModel(){

        ProductDto productDto=new ProductDto("Laptop","LHA","Active",4,1000);
        Product product = new Product();
        product.setCode(productDto.getCode());
        product.setName(productDto.getName());
        assertEquals(product.getName(),productService.convertToProductEntity(productDto).getName());


    }

    @Test
    public void convertToProductDto_WhenValidProductIsPass_ThenReturnProductDto(){

        Product product=new Product();
        product.setName("Laptop");
        product.setCode("LHA44441");
        ProductDto productDto=new ProductDto();
        productDto.setName("Laptop");
        assertEquals(productDto.getName(),productService.convertToProductDto(product).getName());
    }

    @Test
    public void addProduct_WhenValidProductIsAdded_ThenReturnProductDto(){







    }




}
