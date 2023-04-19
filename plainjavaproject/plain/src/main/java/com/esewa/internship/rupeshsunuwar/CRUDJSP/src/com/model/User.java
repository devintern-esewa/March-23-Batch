package com.model;

public class User {
    int id;
    String name;
    String password;
    String email;
    String sex;
    String country;

    public User(){

    }
    public User(int id,String name, String password, String email, String sex, String country) {
        this.id=id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
