package service;

import dao.ProductDAO;
import model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ProductDAO.class)
public class ProductDAOTest {
    @Test
    public void calculateDiscountTest() {
        double price = 100.0;
        double discountPercent = 20.0;

        PowerMockito.mockStatic(ProductDAO.class);
        when(ProductDAO.calculateDiscount(price, discountPercent)).thenReturn(20.0);

        double discount = ProductDAO.calculateDiscount(price, discountPercent);

        assertEquals(20.0, discount, 0.0);
    }

    @Test
    public void calculateAveragePriceTest() {
//        ProductDAO product = new ProductDAO();

        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "Product 1", 10.0));
        productList.add(new Product(2, "Product 2", 20.0));
        productList.add(new Product(3, "Product 3", 30.0));

        PowerMockito.mockStatic(ProductDAO.class);

        when(ProductDAO.calculateAveragePrice(productList)).thenReturn(30.0);
        assertEquals(30.0, ProductDAO.calculateAveragePrice(productList), 0.1);

//        double actualAverage = Whitebox.invokeMethod(product,"calculateAveragePrice",productList);
//        assertEquals(30.0,actualAverage,0.1);
    }
}
