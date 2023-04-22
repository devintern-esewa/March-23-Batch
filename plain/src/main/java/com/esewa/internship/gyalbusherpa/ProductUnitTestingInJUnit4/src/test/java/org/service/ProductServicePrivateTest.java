package org.service;

import org.dao.ProductDao;
import org.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.powermock.reflect.internal.WhiteboxImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ProductDao.class)
public class ProductServicePrivateTest {

//    @InjectMocks
//    ProductService productService;

    @Test
    public void testCalculateDiscount() throws Exception {
        ProductDao product = new ProductDao();
        Product p = new Product(1, "Product 1", 100.0);

        double actualDiscount = WhiteboxImpl.invokeMethod(product, "calculateDiscount", p);

        assertEquals(10.0, actualDiscount,0.1);
    }
}
