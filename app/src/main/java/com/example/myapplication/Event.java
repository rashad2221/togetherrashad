package com.example.myapplication;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Event {
    private String houseName;
    private String name;
    private String date;
    private String location;
    private String time;
    private String description;
    private String neededNum;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNeededNum(String num) {this.neededNum = num;}

    public String getNeededNum(){return this.neededNum;}

    public String getAddress(){return this.address;}

    public void setAddress(String address){this.address = address;}

    public Event(String houseName, String name, String date, String location, String address, String time, String description, String num) {
        this.houseName = houseName;
        this.name = name;
        this.date = date;
        this.location = location;
        this.address = address;
        this.time = time;
        this.description = description;
        this.neededNum = num;
    }

    public Event() {

    }

}
