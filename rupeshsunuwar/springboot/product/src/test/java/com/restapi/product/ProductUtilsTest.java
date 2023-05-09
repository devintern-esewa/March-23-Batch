package com.restapi.product;


import com.restapi.product.dto.ProductDto;
import com.restapi.product.utils.ProductUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static com.restapi.product.utils.ProductUtils.calculateDiscount;
import static com.restapi.product.utils.ProductUtils.calculateTotalPrice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(ProductUtils.class)
public class ProductUtilsTest {


    @Test
    public void calculateTotalPrice_ThenReturnTotalPrice() {

        List<ProductDto> productDto = new ArrayList<>();
        ProductDto productDto1 = new ProductDto("Laptop", 3.0, "Electronics", "Best");
        ProductDto productDto2 = new ProductDto("Mobile", 5.0, "Electronics", "Best");
        productDto.add(productDto1);
        productDto.add(productDto2);
//        PowerMockito.mockStatic(ProductUtils.class);
        Double price = ProductUtils.calculateTotalPrice(productDto);
        assertEquals(8.0, price, 0.1);

    }

    @Test
    public void calculateDiscount_WhenTotalPriceIsLessThan10000_ThenReturnDiscount() {

        PowerMockito.mockStatic(ProductUtils.class);
        assertEquals(0, calculateDiscount(100));


    }


}
