package org.example.service;

import org.example.customException.IdAlreadyExists;
import org.example.customException.InvalidIdException;
import org.example.dao.ProductDao;
import org.example.model.Product;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    //implements ProductService that depends on ProductDao through constructor injection
    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public boolean addNewProduct(Product product) {
        Optional<Product> product1 = productDao.getProductById(product.getProductId());
        if (product.getProductId() == 0 || product.getProductName().equals("") || product.getPrice() == 0.0f) {
            throw new InvalidIdException("Values are incorrect");
        }
        /*
        it checks if product1 has value of not
        if value found, throw exception
        else retrun productDao.addNewProduct
         */
        if (product1.isPresent()) {
            throw new IdAlreadyExists("Product with already exists");
        }

        return productDao.addNewProduct(product);
    }

    public boolean deleteById(int productId) {

        Optional<Product> productById = productDao.getProductById(productId);
        if (productId <= 0 || productById.isEmpty()) {
            throw new InvalidIdException("Product " + productId + " Doesn't exists to delete");
        }


        return productDao.deleteById(productId);
    }

    public boolean updateProduct(Product product) {
        Optional<Product> productById = productDao.getProductById(product.getProductId());
        if (product.getProductId() <= 0 || productById.isEmpty()) {
            throw new InvalidIdException("Product " + product.getProductId() + " Doesn't exists");
        }

        return productDao.updateProduct(product);
    }

    public Product getById(int productId) {
        Optional<Product> productById = productDao.getProductById(productId);
        if (productById.isEmpty() || productId <= 0) {
            throw new InvalidIdException("Product with " + productId + " doesnt exists");
        }
        return productById.get();
    }


}
