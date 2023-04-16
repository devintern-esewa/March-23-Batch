package org.example.model;

public class Product {
    //hey let you store data in a program that a specific place in the program can only access.
    //to read and change value we need getter and setter method
    private int product_id;
    private String product_name;
    private float price;

    public Product(int product_id, String product_name, float price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
