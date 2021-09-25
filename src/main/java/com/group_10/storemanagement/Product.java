package com.group_10.storemanagement;

public class Product {
    public final String product_name;
    public final double unit_price;
    public final String category;
    public final int id;

    public Product(int id, String product_name, double unit_price, String category) {
        this.product_name = product_name;
        this.unit_price = unit_price;
        this.category = category;
        this.id = id;
    }

    @Override
    public String toString() {
        return product_name;
    }
}
