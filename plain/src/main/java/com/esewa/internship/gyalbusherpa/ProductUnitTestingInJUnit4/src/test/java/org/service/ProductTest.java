package org.service;

import org.dao.ProductDao;
import org.entity.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ProductService.class, ProductDao.class})
public class ProductTest {

    @Mock
    private ProductDao productDao;
    @InjectMocks
    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(ProductDao.class);
    }

    @Test
    public void testGetAveragePriceOfProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "Product 1", 10.0));
        productList.add(new Product(2, "Product 2", 20.0));
        productList.add(new Product(3, "Product 3", 30.0));

        when(productDao.getAllProducts()).thenReturn(productList);
        PowerMockito.when(ProductDao.calculateAveragePrice(productList)).thenReturn(30.0);

        assertEquals(30.0, productService.getAveragePriceOfProducts(), 0.1);
    }
}
