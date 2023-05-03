/*
package com.inventoryDb.service;

import com.inventoryDb.enums.ProductEnum;
import com.inventoryDb.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ProductServiceImpl.class)
public class ProductServiceImplTestWithPowerMockTest {
    @Mock
    private BufferedReader bufferedReader;

    @Mock
    private FileReader fileReader;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testConvertCsvDataInFilePathToProduct() throws Exception {

        when(bufferedReader.readLine())
                .thenReturn("name,code,description,quantity,price")
                .thenReturn("Product1,P1,Description1,10,5.00")
                .thenReturn("Product2,P2,Description2,20,10.00")
                .thenReturn("Product3,P3,Description3,30,15.00")
                .thenReturn(null);

        when(fileReader.ready())
                .thenReturn(true, true, true, false);

        String testFilePath = "/path/to/test.csv";

        whenNew(FileReader.class).withArguments(testFilePath).thenReturn(fileReader);
        whenNew(BufferedReader.class).withArguments(fileReader).thenReturn(bufferedReader);


        List<Product> products = productService.convertCsvDataInFilePathToProduct(testFilePath);

        assertEquals(3, products.size());

        assertEquals("Product1", products.get(0).getName());
        assertEquals("P1", products.get(0).getCode());
        assertEquals(ProductEnum.ACTIVE, products.get(0).getProductStatus());
        assertEquals(10, products.get(0).getQuantity(), 0.001);
        assertEquals(5.00, products.get(0).getPrice(), 0.001);

    }
}
*/
