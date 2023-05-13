package org.service;

import org.dao.ProductDao;
import org.entity.Product;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product getProductById(int id) {
        Optional<Product> optionalProduct = productDao.getProductById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new RuntimeException("Product with Id " + id + " does not exist");
        }
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public List<Product> getAllProductsWithSameName(String name) {
        List<Product> pr = productDao.getAllProducts()
                .stream()
                .filter(product -> product.getName().equals(name)).
                collect(Collectors.toList());
        return pr;
    }

    public boolean createProduct(Product product) {
        return productDao.createProduct(product);
    }

    public boolean updateProduct(Product product) {
        boolean success = productDao.updateProduct(product);
        if (!success) {
            throw new RuntimeException("Unable to update product with Id " + product.getId());
        }
        return true;
    }

    public boolean deleteProduct(Product product) {
        boolean success = productDao.deleteProduct(product);
        if (!success) {
            throw new RuntimeException("Unable to delete product with Id " + product.getId());
        }
        return true;
    }

    public double getTotalPriceOfProducts() {
        List<Product> products = productDao.getAllProducts();
        double totalPrice = 0.0;
        for (Product p : products) {
            totalPrice += p.getPrice();
        }
        return totalPrice;
    }

    public double getAveragePriceOfProducts() {
        List<Product> products = productDao.getAllProducts();
        return ProductDao.calculateAveragePrice(products);
    }



}
