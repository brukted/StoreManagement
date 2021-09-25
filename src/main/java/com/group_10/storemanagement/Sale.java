package com.group_10.storemanagement;

import java.util.ArrayList;
import java.util.Date;

public class Sale {
    public final Date date;
    public final float amount;
    public final int employee_id;
    public final int customer_id;
    public final ArrayList<SaleItem> items;

    public Sale(Date date, float amount, int employee_id, int customer_id, ArrayList<SaleItem> items) {
        this.date = date;
        this.amount = amount;
        this.employee_id = employee_id;
        this.customer_id = customer_id;
        this.items = items;
    }
}
