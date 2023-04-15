package org.service;

import org.dao.ProductDao;
import org.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ProductDao.class)
public class ProductServicePrivateTest {

//    @InjectMocks
//    ProductService productService;

    @Test
    public void testGetAveragePriceOfProducts() throws Exception {
        ProductDao product = new ProductDao();

        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "Product 1", 10.0));
        productList.add(new Product(2, "Product 2", 20.0));
        productList.add(new Product(3, "Product 3", 30.0));

//        PowerMockito.mockStatic(ProductDao.class);
//
//        when(ProductDao.calculateAveragePrice(productList)).thenReturn(30.0);
//        assertEquals(30.0,ProductDao.calculateAveragePrice(productList),0.1);

        double actualAverage = Whitebox.invokeMethod(product,"calculateAveragePrice",productList);
        Assert.assertEquals(30.0,actualAverage,0.1);
    }

    @Test
    public void testCalculateDiscount() throws Exception {
        ProductDao product = new ProductDao();
        Product p = new Product(1, "Product 1", 100.0);

        double actualDiscount = Whitebox.invokeMethod(product, "calculateDiscount", p);

        Assert.assertEquals(10.0, actualDiscount,0.1);
    }
}
