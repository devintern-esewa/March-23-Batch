package org.example.dao;

import org.example.model.Product;

import java.util.List;
import java.util.Optional;

/*This class is responsible to get data from a datasource which can be database / xml or any other storage mechanism.
to provide abstraction and decoupling between the service layer and the data access layer.

 */
public interface ProductDao {
    List<Product> getAllProducts();

    public boolean addNewProduct(Product product);

    Optional<Product> getProductById(int id);

    boolean deleteById(int id);

    boolean updateProduct( Product product);
}
