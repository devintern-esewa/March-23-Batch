package org.example;

import org.example.dao.ProductDaoImpl;
import org.example.model.Product;
import org.example.service.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.example.util.TotalUtil.total;


public class App {
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product(1, "Biscuit", 20.0f);
        Product product2 = new Product(2, "Wai Wai", 22.50f);
        Product product3 = new Product(3, "Coke", 150.0f);
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        ProductDaoImpl productDao = new ProductDaoImpl(productList);
        ProductServiceImpl productServiceImpl = new ProductServiceImpl(productDao);
        System.out.println("############################################");
        System.out.print("\n");
        System.out.println("Get All Products ");

        List<Product> products = productServiceImpl.getAllProducts();
        for (Product product : products
        ) {
            System.out.println(product.getProductId() + " " + product.getProductName() + " " + product.getPrice());
        }
        System.out.println("############################################");
        System.out.print("\n");
        System.out.println("Get product by id");
        Product getID = productServiceImpl.getById(2);
        System.out.println(getID);

        System.out.println("############################################");
        System.out.print("\n");
        Product product4 = new Product(4, "Cokeee", 160.0f);
        productServiceImpl.addNewProduct(product4);
        System.out.println("After adding product");
        for (Product product : products
        ) {
            System.out.println(product.getProductId() + " " + product.getProductName() + " " + product.getPrice());
        }

        System.out.println("############################################");
        System.out.print("\n");
        System.out.println("Deleting the data");
        productServiceImpl.deleteById(2);
        System.out.println("After deleting product");
        for (Product product : products
        ) {
            System.out.println(product.getProductId() + " " + product.getProductName() + " " + product.getPrice());
        }

        System.out.println("############################################");
        System.out.print("\n");

        System.out.println("Updating the data");
        Product product5 = new Product(1, "Cola", 10.0f);
        productServiceImpl.updateProduct(product5);
        System.out.println("After updating");
        for (Product product : products
        ) {
            System.out.println(product.getProductId() + " " + product.getProductName() + " " + product.getPrice());
        }
//
//        System.out.print("\n");
//        System.out.println(total(productList));
    }

}
