package com.group_10.storemanagement;

public class Store {
    public final String name;
    public final String city;
    public final String street;
    public final String house_number;
    public final int id;

    public Store(String name, String city, String street, String house_number, int id) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.house_number = house_number;
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
