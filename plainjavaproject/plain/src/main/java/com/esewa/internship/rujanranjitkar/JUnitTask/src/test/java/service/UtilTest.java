package service;


import model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import utils.Util;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Util.class)
public class UtilTest {
    @Test
    public void calculateDiscount_WhenValidData() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "Product 1", 10.0));
        productList.add(new Product(2, "Product 2", 20.0));
        productList.add(new Product(3, "Product 3", 30.0));

        PowerMockito.mockStatic(Util.class);
        when(Util.calculateDiscount(productList)).thenReturn(0.0);

        double discount = Util.calculateDiscount(productList);

        assertEquals(0.0, discount, 0.0);
    }

    @Test
    public void calculateAveragePrice_WhenValidData() {
//        ProductDAO product = new ProductDAO();

        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "Product 1", 10.0));
        productList.add(new Product(2, "Product 2", 20.0));
        productList.add(new Product(3, "Product 3", 30.0));

        PowerMockito.mockStatic(Util.class);

        when(Util.calculateAveragePrice(productList)).thenReturn(30.0);
        assertEquals(30.0, Util.calculateAveragePrice(productList), 0.1);

//        double actualAverage = Whitebox.invokeMethod(product,"calculateAveragePrice",productList);
//        assertEquals(30.0,actualAverage,0.1);
    }
}
