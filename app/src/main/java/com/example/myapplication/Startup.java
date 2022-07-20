package com.example.myapplication;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Startup {

    private String name;
    private String statusQuo;
    private boolean hasSolution;

    // Constructor
    public Startup(String name, String statusQuo, boolean hasSolution) {
        this.name = name;
        this.statusQuo = statusQuo;
        this.hasSolution = hasSolution;
    }

    public Startup() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getStatusQuo() {
        return this.statusQuo;
    }

    public void setStatusQuo(String statusQuo) {
        this.statusQuo = statusQuo;
    }

    public boolean isHasStatusQuo() {
        return this.hasSolution;
    }

    public void setHasSolution(boolean hasSolution) {
        this.hasSolution = hasSolution;
    }

    public String toString() {
        return "StartUp{" +
                "name='" + this.name + '\'' +
                ", statusQuo='" + this.statusQuo + '\'' +
                ", hasSolution='" + this.hasSolution +
                '}';
    }




}
