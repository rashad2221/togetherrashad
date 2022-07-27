package com.example.myapplication;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    private String name;
    private String email;
    private String password;
    private String location;
    private String address;
    private String idNum;
    private String phoneNum;
    private String birthday;
    private boolean isTeenager;

    // Constructor
    public User(String name, String email, String password, String location, String idNum, String phoneNum, String birthday) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
        this.idNum = idNum;
        this.phoneNum = phoneNum;
        this.birthday = birthday;
        this.address = "";
        this.isTeenager = true;
    }

    public User(String name, String email, String password, String location, String idNum, String phoneNum, String birthday, boolean isTeenager, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
        this.idNum = idNum;
        this.phoneNum = phoneNum;
        this.birthday = birthday;
        this.isTeenager = isTeenager;
        this.address = address;
    }

    public User() {

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public boolean getIsTeenager() {return this.isTeenager;}

    public void setIsTeenager(boolean isTeenager) {this.isTeenager = isTeenager;}

    public String getAddress() {return  this.address;}

    public void setAddress(String address) {this.address = address;}

    @Override
    public String toString() {
        return "User{" +
                "name='" + this.name + '\'' +
                ", email='" + this.email + '\'' +
                ", password='" + this.password +
                '}';
    }




}
