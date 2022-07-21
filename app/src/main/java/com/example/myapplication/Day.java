package com.example.myapplication;

import java.util.ArrayList;

public class Day {
    private ArrayList<Event> events;

    public Day(ArrayList<Event> events) {
        this.events = events;
    }

    public Day() {

    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
