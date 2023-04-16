package dao;

import model.Product;

import java.util.List;

public class ProductDAO {
    private List<Product> productList;

    public ProductDAO() {
    }

    public ProductDAO(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public Product getProductById(int id) {
        for (Product product : productList) {
            if (id == product.getProduct_id())
                return product;
        }
        return null;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void updateProduct(Product product) {
        int index = productList.indexOf(getProductById(product.getProduct_id()));
        if (index != -1) {
            productList.set(index, product);
        }
    }

    public void deleteProduct(int id) {
        Product productToDelete = getProductById(id);
        if (productToDelete != null)
            productList.remove(productToDelete);
    }

    public static double calculateDiscount(double price, double discountPercent) {
        return price * discountPercent / 100.0;
    }

    public static double calculateAveragePrice(List<Product> products) {
        double sum = 0.0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return products.isEmpty() ? 0.0 : sum / products.size();
    }
}
