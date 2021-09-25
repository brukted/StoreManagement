package com.group_10.storemanagement;

import javafx.beans.property.SimpleIntegerProperty;

public class Stock {
    public final String store_name;
    public final String product_name;
    public final int quantity;

    public Stock( String store_name, String product_name, int quantity) {
        this.store_name = store_name;
        this.product_name = product_name;
        this.quantity = quantity;
    }
}
