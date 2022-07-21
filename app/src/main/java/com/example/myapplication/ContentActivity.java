package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContentActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    ListView eventsListView;
    ArrayList<Event> events;
    ArrayAdapter<Event> arrayAdapter;
    FirebaseDatabase database;
    Day day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        mAuth = FirebaseAuth.getInstance();
        eventsListView = findViewById(R.id.eventsListView);
        events = new ArrayList<Event>();

        Event bingo = new Event("Bingo Night", "18/01/22", "Tel Aviv Retirement Home", "16:00", "Support thr retirement home in the event");
        Event chess = new Event("Chess Tournament", "18/01/22", "Jerusalem Retirement Home", "12:00", "Play with the elders and run the tournament");
        Event dancing = new Event("Dancing Night", "18/01/22", "Ramat Ran Retirement Home", "20:00", "Dance with the elders and run the event");
        events.add(chess);
        events.add(bingo);
        events.add(dancing);
        arrayAdapter = new EventArrayAdapter(ContentActivity.this, R.layout.custom_row, events);
        eventsListView.setAdapter(arrayAdapter);


        // database = FirebaseDatabase.getInstance("https://meetdevworkshop-82ffa-default-rtdb.europe-west1.firebasedatabase.app/");
        // database.getReference("Users").child("2").setValue(user);

        /*
        DatabaseReference users_data = database.getReference();
        users_data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()) {
                    Event event = data.getValue(Event.class);
                    events.add(event);
                }
                // usersListView = findViewById(R.id.startupslistview);
                // arrayAdapter = new UserArrayAdapter(ContentActivity.this, R.layout.custom_row, users);
                // usersListView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.sign_out:
                mAuth.signOut();
                // Toast.makeText(this, "sign out", Toast.LENGTH_SHORT);
                intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                break;
            case R.id.calendar:
                intent = new Intent(this, Calendar.class);
                startActivity(intent);
            // default:
                // return super.onOptionsItemSelected(item);
        }
        return true;
    }
}