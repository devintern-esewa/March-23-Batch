package service;

import dao.ProductDAO;
import model.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    public Product getProductById(int id) {
        Product product = productDAO.getProductById(id);
        if (product != null)
            return product;
        else if (id <= 0) throw new IllegalArgumentException("Invalid id");
        return null;
    }

    @Override
    public void addProduct(Product product) {
        if (product.getProduct_id() <= 0) throw new IllegalArgumentException("Invalid id");
        else if (productDAO.getProductById(product.getProduct_id()) != null)
            throw new IllegalArgumentException("Id already exists");
        productDAO.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    @Override
    public void deleteProduct(int id) {
        if (id <= 0) throw new IllegalArgumentException("Invalid id");
        productDAO.deleteProduct(id);
    }
}
