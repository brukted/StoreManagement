package com.group_10.storemanagement;

public class Store {
    private String name;
    private String city;
    private String street;
    private String house_number;
    private int id;

    public Store(String name, String city, String street, String house_number, int id) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.house_number = house_number;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
