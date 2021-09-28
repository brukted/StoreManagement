package com.group_10.storemanagement;

public class Product {
    private String product_name;
    private double unit_price;
    private String category;
    private int id;

    public Product(int id, String product_name, double unit_price, String category) {
        this.product_name = product_name;
        this.unit_price = unit_price;
        this.category = category;
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return product_name;
    }
}
