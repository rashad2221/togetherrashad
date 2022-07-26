package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DayEvents extends AppCompatActivity {
    public String dateString;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_events);

        Bundle bundle = getIntent().getExtras();
        dateString = bundle.getString("date");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_events);
        new Day().readEvents(new Day.DataStatus() {
            ArrayList filtered = new ArrayList();

            @Override
            public void DataIsLoaded(List<Event> events, List<String> keys) {

                for (Event date : events) {
                    if (date.getDate().equals(dateString)){
                        filtered.add(date);
                        String beep = "bee";
                    }

                }

                //HashMap <Event, String> values = (HashMap<Event, String>) events;
                new RecyclerView_config().setConfig(recyclerView, DayEvents.this, events, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

    }
}