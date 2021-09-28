package com.group_10.storemanagement;

import java.util.ArrayList;
import java.util.Date;

public class Sale {
    private int id;
    private Date date;
    private int store_id;
    private int employee_id;
    private int customer_id;
    private ArrayList<SaleItem> items;

    public Sale(int id, Date date, int store_id, int employee_id, int customer_id, ArrayList<SaleItem> items) {
        this.id = id;
        this.date = date;
        this.store_id = store_id;
        this.employee_id = employee_id;
        this.customer_id = customer_id;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public ArrayList<SaleItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<SaleItem> items) {
        this.items = items;
    }
}
