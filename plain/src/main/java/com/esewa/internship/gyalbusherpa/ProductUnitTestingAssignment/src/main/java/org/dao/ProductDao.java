package org.dao;

import org.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private final List<Product> productList;

    public ProductDao() {
        productList = new ArrayList<>();
    }

    public Product getProductById(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public boolean createProduct(Product product) {
        return productList.add(product);
    }

    public boolean updateProduct(Product product) {
        for (Product products : productList) {
            if (products.getId() == product.getId()) {
                products.setName(product.getName());
                products.setPrice(product.getPrice());
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(Product product) {
        return productList.remove(product);
    }
}
