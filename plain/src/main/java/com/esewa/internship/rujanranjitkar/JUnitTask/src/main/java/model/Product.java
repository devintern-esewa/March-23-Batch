package model;

public class Product {
    private int product_id;
    private String productName;
    private double price;

    public Product(int product_id, String productName, double price) {
        this.product_id = product_id;
        this.productName = productName;
        this.price = price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
