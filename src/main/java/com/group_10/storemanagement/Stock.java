package com.group_10.storemanagement;

public class Stock {
    private String store_name;
    private String product_name;
    private int quantity;

    public Stock( String store_name, String product_name, int quantity) {
        this.store_name = store_name;
        this.product_name = product_name;
        this.quantity = quantity;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
