package com.group_10.storemanagement;

public class SaleItemView {

    private  int quantity;
    private  Product product;
    private  String productName;
    private  double price;
    private  double total;

    public SaleItemView(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
        this.productName = product.getProduct_name();
        this.price = product.getUnit_price();
        this.total = quantity * price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
