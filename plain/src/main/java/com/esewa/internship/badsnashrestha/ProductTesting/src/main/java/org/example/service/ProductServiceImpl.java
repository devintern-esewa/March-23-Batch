package org.example.service;

import org.example.dao.ProductDao;
import org.example.model.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService{
    //implements ProductService that depends on ProductDao through constructor injection
    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public boolean addNewProduct(Product product){
        if(product.getProduct_id()==0 || product.getProduct_name().equals("")||product.getPrice()==0.0f){
            throw new RuntimeException("Values are incorrect");
        }
        if(productDao.addNewProduct(product)){
            return true;
        }
        return false;
    }

    public boolean deleteById(int id){
        Product p=productDao.getProductById(id);
        if(id<=0){
            throw new RuntimeException("Product Doesn't exists");
        }
        else if(p!=null){
            productDao.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateProduct(int id){
        Product p=productDao.getProductById(id);
        if(id<=0){
            throw new RuntimeException("Product Doesn't exists");
        }
        else if(productDao.updateProduct(id)){
            return true;
        }
        return false;
    }
}
