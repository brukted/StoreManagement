package com.group_10.storemanagement;

public class SaleItemView {

    final int quantity;
    final Product product;
    final String productName;
    final double price;
    final double total;

    public SaleItemView(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
        this.productName = product.product_name;
        this.price = product.unit_price;
        this.total = quantity * price;
    }
}
