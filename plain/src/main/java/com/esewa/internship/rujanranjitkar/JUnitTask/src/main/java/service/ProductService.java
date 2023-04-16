package service;

import model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();

    public Product getProductById(int id);

    public void addProduct(Product product);

    public void updateProduct(Product product);

    public void deleteProduct(int id);

}
