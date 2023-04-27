package dao;

import model.Product;

import java.util.List;
import java.util.Optional;

public class ProductDAO {
    private List<Product> productList;

    public ProductDAO(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public Optional<Product> getProductById(int id) {
        return productList.stream()
                .filter(product -> product.getProductId() == id)
                .findFirst();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void updateProduct(Product product) {
        int index = productList.indexOf(getProductById(product.getProductId()));
        if (index != -1) {
            productList.set(index, product);
        }
    }

    public void deleteProduct(int id) {
        Optional<Product> productToDelete = getProductById(id);
        if (productToDelete.isPresent())
            productList.remove(productToDelete.get());
    }


}
