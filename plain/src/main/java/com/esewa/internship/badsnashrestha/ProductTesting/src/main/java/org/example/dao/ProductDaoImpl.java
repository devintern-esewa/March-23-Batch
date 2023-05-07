package org.example.dao;

import org.example.model.Product;

import java.util.List;
import java.util.Optional;

/*This class is responsible to get data from a datasource which can be database / xml or any other storage mechanism.
to provide abstraction and decoupling between the service layer and the data access layer.

 */
public class ProductDaoImpl implements ProductDao {
    List<Product> productList;

    public ProductDaoImpl(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public boolean addNewProduct(Product product) {
        return productList.add(product);
    }

    @Override
    public Optional<Product> getProductById(int productId) {
        /*
        stream()=> creates an stream of list
        .filer()=>filter outs the product whose id matches from the stream
        .findFirst()=>returns an Optional<Product> object representing the first product of the stream
        an empty Optional<Product> is returned if not found
         */
        return productList.stream()
                .filter(product -> product.getProductId() == productId)
                .findFirst();
    }

    @Override
    public boolean deleteById(int productId) {
        /*
         If the productId exists in the productList, then productById will contain an Optional<Product> object that holds the corresponding Product object.
         Otherwise, productById will be an empty Optional<Product> object.
         */
        Optional<Product> productById = getProductById(productId);

        if (productById.isPresent()) {
            //to  retrieve the value and remove it
            productList.remove(productById.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        /*
        productById.get() returns the Product object wrapped inside the Optional instance productById
         */
        Optional<Product> productById = getProductById(product.getProductId());
        if (productById.isPresent()) {
            Product existingProduct = productById.get();
            existingProduct.setProductName(product.getProductName());
            existingProduct.setPrice(product.getPrice());
            return true;
        }
        return false;
    }
}
