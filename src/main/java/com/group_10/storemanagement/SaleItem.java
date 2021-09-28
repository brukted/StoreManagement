package com.group_10.storemanagement;

public class SaleItem {
    private String product_name;
    private int quantity;

    public SaleItem(String product_name, int quantity) {
        this.product_name = product_name;
        this.quantity = quantity;
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
