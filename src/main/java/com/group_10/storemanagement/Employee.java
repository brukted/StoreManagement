package com.group_10.storemanagement;

public class Employee {
    public final int id;
    public final String first_name;
    public final String last_name;
    public final String email;
    public final String phone_number;
    public final int age;
    public final String sex;
    public final String city;
    public final String street;
    public final int house_number;
    public final int store_id;

    public Employee(int id, String first_name, String last_name, String email, String phone_number, int age, String sex, String city, String street, int house_number, int store_id) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.age = age;
        this.sex = sex;
        this.city = city;
        this.street = street;
        this.house_number = house_number;
        this.store_id = store_id;
    }
}
