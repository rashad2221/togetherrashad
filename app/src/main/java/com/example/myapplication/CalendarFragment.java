package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class CalendarFragment extends Fragment {
    FirebaseAuth mAuth;
    CalendarView calendarView;
    TextView myDate;


    public static CalendarFragment newInstance() {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    public CalendarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);


        mAuth = FirebaseAuth.getInstance();
        calendarView = (CalendarView) rootView.findViewById(R.id.calendarView1);
        myDate = rootView.findViewById(R.id.DateText1);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = i2 + "/" + (i1 + 1) +"/" + i;
                myDate.setText(date);
                Intent intent = new Intent(getActivity(), DayEvents.class);
                intent.putExtra("date", date);

                startActivity(intent);
            }
        });

        return rootView;

    }
}