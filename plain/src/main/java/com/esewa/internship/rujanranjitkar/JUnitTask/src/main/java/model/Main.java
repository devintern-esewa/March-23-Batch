package model;

import dao.ProductDAO;
import service.ProductService;
import service.ProductServiceImpl;

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
            System.out.println(product.getProduct_id() + " " + product.getProductName() + " " + product.getPrice());
        }

        //Getting product by id
        Product productById = productService.getProductById(3);
        System.out.println();
        System.out.println("Select product by specific id:");
        System.out.println(productById.getProduct_id() + " " + productById.getProductName() + " " + productById.getPrice());

        // Adding new product
        Product newProduct = new Product(4, "Oppo", 20000.0);
        productService.addProduct(newProduct);
        System.out.println();
        System.out.println("New product added:");
        System.out.println(newProduct.getProduct_id() + " " + newProduct.getProductName() + " " + newProduct.getPrice());

        //Get all products after adding new product
        System.out.println();
        System.out.println("All Products:");
        for (Product product : allProducts) {
            System.out.println(product.getProduct_id() + " " + product.getProductName() + " " + product.getPrice());
        }

        //Update specific product
        Product productToUpdate = productService.getProductById(1);
        productToUpdate.setPrice(1100000.0);
        productService.updateProduct(productToUpdate);
        System.out.println();
        System.out.println("Product updated:");
        System.out.println(productToUpdate.getProduct_id() + " " + productToUpdate.getProductName() + " " + productToUpdate.getPrice());

        //Get all products after updating specific product
        System.out.println();
        System.out.println("All Products:");
        for (Product product : allProducts) {
            System.out.println(product.getProduct_id() + " " + product.getProductName() + " " + product.getPrice());
        }

        //Delete specific product
        productService.deleteProduct(1);
        System.out.println();
        System.out.println("Product list after deletion:");
        for (Product product : allProducts) {
            System.out.println(product.getProduct_id() + " " + product.getProductName() + " " + product.getPrice());
        }

        //Calculating discount using static method of ProductDAO
        double discount = ProductDAO.calculateDiscount(1000.0, 20.0);
        System.out.println();
        System.out.println("Discount: " + discount);

        System.out.println("Average price is:");
        System.out.println(ProductDAO.calculateAveragePrice(productList));
    }
}
