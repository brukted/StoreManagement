package com.group_10.storemanagement;

public class SaleItemView {

    final Integer quantity;
    final Product product;
    final String productName;
    final Double price;
    final Double total;

    public SaleItemView(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
        this.productName = product.product_name;
        this.price = product.unit_price;
        this.total = quantity * price;
    }
}
