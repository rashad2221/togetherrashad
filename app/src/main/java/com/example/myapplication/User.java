package com.example.myapplication;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    private String name;
    private String email;
    private String password;
    private String location;
    private String idNum;
    private String phoneNum;
    private String birthday;

    // Constructor
    public User(String name, String email, String password, String location, String idNum, String phoneNum, String birthday) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
        this.idNum = idNum;
        this.phoneNum = phoneNum;
        this.birthday = birthday;
    }

    public User() {

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

    @Override
    public String toString() {
        return "User{" +
                "name='" + this.name + '\'' +
                ", email='" + this.email + '\'' +
                ", password='" + this.password +
                '}';
    }




}
