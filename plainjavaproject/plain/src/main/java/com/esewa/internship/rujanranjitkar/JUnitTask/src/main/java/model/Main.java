package model;

import customException.InvalidIdException;
import dao.ProductDAO;
import service.ProductService;
import service.ProductServiceImpl;
import utils.Util;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();
        Product p1 = new Product(1, "Iphone", 100000.0);
        Product p2 = new Product(2, "Samsung", 90000.0);
        Product p3 = new Product(3, "Redmi", 50000.0);
        productList.add(p1);
        productList.add(p2);
        productList.add(p3);

        ProductDAO productDAO = new ProductDAO(productList);
        ProductService productService = new ProductServiceImpl(productDAO);

        //Getting all products
        List<Product> allProducts = productService.getAllProducts();
        System.out.println("All Products:");
        for (Product product : allProducts) {
            System.out.println(product.getProductId() + " " + product.getProductName() + " " + product.getPrice());
        }
        System.out.print("\n");

        //Getting product by id
        Product productById = productService.getProductById(3);
        System.out.println("Select product by specific id:");
        System.out.println(productById.getProductId() + " " + productById.getProductName() + " " + productById.getPrice());
        System.out.print("\n");

//         Adding new product
        Product newProduct = new Product(4, "Oppo", 20000.0);
        productService.addProduct(newProduct);
        System.out.println("New product added:");
        System.out.println(newProduct.getProductId() + " " + newProduct.getProductName() + " " + newProduct.getPrice());

        //Get all products after adding new product
        System.out.print("\n");
        System.out.println("All Products:");
        for (Product product : allProducts) {
            System.out.println(product.getProductId() + " " + product.getProductName() + " " + product.getPrice());
        }
        System.out.print("\n");

//        //Update specific product
        Product productToUpdate = productService.getProductById(1);
        productToUpdate.setPrice(1100000.0);
        productService.updateProduct(productToUpdate);
        System.out.println("Product updated:");
        System.out.println(productToUpdate.getProductId() + " " + productToUpdate.getProductName() + " " + productToUpdate.getPrice());

//        Get all products after updating specific product
        System.out.print("\n");
        System.out.println("All Products:");
        for (Product product : allProducts) {
            System.out.println(product.getProductId() + " " + product.getProductName() + " " + product.getPrice());
        }
        System.out.print("\n");

        //Delete specific product
        productService.deleteProduct(1);
        System.out.println("Product list after deletion:");
        for (Product product : allProducts) {
            System.out.println(product.getProductId() + " " + product.getProductName() + " " + product.getPrice());
        }
        System.out.print("\n");

        //Calculating discount using static method
        double discount = Util.calculateDiscount(productList);
        System.out.println("Discount: " + discount);
        System.out.print("\n");

        //Calculating average price
        System.out.println("Average price is:");
        System.out.println(Util.calculateAveragePrice(productList));
    }
}
