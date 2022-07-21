package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DayPlans extends AppCompatActivity {

    TextView relativeDate;

    public static String dateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_plans);

        relativeDate = findViewById(R.id.dateText);
        Bundle bundle = getIntent().getExtras();
        dateString = bundle.getString("date");

        relativeDate.setText(dateString);

    }
}