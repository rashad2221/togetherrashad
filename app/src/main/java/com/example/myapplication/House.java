package com.example.myapplication;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class House {
    private String name;
    private String email;
    private String password;
    private String location;
    private String phoneNum;
    private boolean isTeenager;

    // Constructor
    public House(String name, String email, String password, String location, String phoneNum) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
        this.phoneNum = phoneNum;
        this.isTeenager = false;
    }

    public House() {

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsTeenager() {
        return this.isTeenager;
    }

    @Override
    public String toString() {
        return "House{" +
                "name='" + this.name + '\'' +
                ", email='" + this.email + '\'' +
                ", password='" + this.password +
                '}';
    }




}
