package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Calendar extends AppCompatActivity {
    FirebaseAuth mAuth;
    CalendarView calendarView;
    TextView myDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mAuth = FirebaseAuth.getInstance();
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate = findViewById(R.id.DateText);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = i2 + "/" + (i1 + 1) +"/" + i;
                myDate.setText(date);
                Intent intent = new Intent(Calendar.this, DayEvents.class);
                intent.putExtra("date", date);

                startActivity(intent);
            }
        });


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