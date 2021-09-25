package com.group_10.storemanagement;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer {
    public final int id;
    public final String firstName;
    public final String last_name;
    public final String email;
    public final String phone_number;
    public final int age;
    public final String sex;
    public final String city;
    public final String street;
    public final int house_number;

    public Customer(int id, String firstName, String last_name, String email, String phone_number, int age, String sex, String city, String street, int house_number) {
        this.id = id;
        this.firstName = firstName;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.age = age;
        this.sex = sex;
        this.city = city;
        this.street = street;
        this.house_number = house_number;
    }
}
