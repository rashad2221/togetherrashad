package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DayEvents extends AppCompatActivity {
    public String dateString;
    private RecyclerView recyclerView;
    FirebaseAuth mAuth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_events);

        Bundle bundle = getIntent().getExtras();
        dateString = bundle.getString("date");
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://togethermvp-57663-default-rtdb.firebaseio.com/");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_events);
        new Day().readEvents(new Day.DataStatus() {
            ArrayList filtered = new ArrayList();

            @Override
            public void DataIsLoaded(List<Event> events, List<String> keys) {

                for (Event date : events) {
                    String uid = mAuth.getCurrentUser().getUid();
                    database.getReference("Users").child(uid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            User currentUser= snapshot.getValue(User.class);
                            if(date.getLocation().equals(currentUser.getLocation())) {
                                if (date.getDate().equals(dateString)) {
                                    filtered.add(date);
                                    String beep = "bee";
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                }

                //HashMap <Event, String> values = (HashMap<Event, String>) events;
                new RecyclerView_config().setConfig(recyclerView, DayEvents.this, filtered, keys);
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