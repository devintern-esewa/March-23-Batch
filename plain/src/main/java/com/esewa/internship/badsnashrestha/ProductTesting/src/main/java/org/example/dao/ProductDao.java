package org.example.dao;

import org.example.model.Product;

import java.util.ArrayList;
import java.util.List;

 //This class is responsible to get data from a d-atasource which can be database / xml or any other storage mechanism.
//to provide abstraction and decoupling between the service layer and the data access layer.
public interface ProductDao {
    List<Product> productList=new ArrayList<>();
    List<Product> getAllProducts();

    public boolean addNewProduct(Product product);

    Product getProductById(int id);

    boolean deleteById(int id);

    boolean updateProduct(int id);
}
