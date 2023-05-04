package org.example.ProductTesting;

import org.example.model.Product;
import org.example.service.ProductServiceImpl;
import org.example.util.TotalUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

//put powermock-api-mockito2 dependency
@RunWith(PowerMockRunner.class)
//Which class is to be tested
@PrepareForTest(TotalUtil.class)
public class TotalTestUsingPowerMock {

    @Test
    public void testTotal() {
        List<Product> productList = new ArrayList<>();
        Product product = new Product(1, "Biscuit", 20.0f);
        Product product1 = new Product(2, "Wai Wai", 20.0f);
        Product product2 = new Product(3, "Coke", 20.0f);
        productList.add(product);
        productList.add(product1);
        productList.add(product2);

        //for mocking static method
        PowerMockito.mockStatic(TotalUtil.class);
        when(TotalUtil.total(productList)).thenReturn(60.0f);
        assertEquals(60.0f, TotalUtil.total(productList));

    }

}
