package com.example.myapplication;

public class Elder {
    private String name;
    private String gender;
    private String hobby;

    public Elder(String name, String gender, String hobby) {
        this.name = name;
        this.gender = gender;
        this.hobby = hobby;
    }

    public Elder() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
