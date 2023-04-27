package service;

import customException.IdAlreadyExistException;
import customException.InvalidIdException;
import dao.ProductDAO;
import model.Product;

import java.util.List;
import java.util.Optional;

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
        Optional<Product> product = productDAO.getProductById(id);
        if (id <= 0 || product.isEmpty()) throw new InvalidIdException("Invalid id");
        return product.get();
    }

    @Override
    public void addProduct(Product product) {
        Optional<Product> product1 = productDAO.getProductById(product.getProductId());
        if (product.getProductId() <= 0) throw new InvalidIdException("Invalid id");
        else if (product1.isPresent())
            throw new IdAlreadyExistException("Id already exists");
        productDAO.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    @Override
    public void deleteProduct(int id) {
        if (id <= 0) throw new InvalidIdException("Invalid id");
        productDAO.deleteProduct(id);
    }
}
