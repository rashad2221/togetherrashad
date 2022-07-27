package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class AddingEvents extends AppCompatActivity implements View.OnClickListener{
    EditText name, date, time, neededNum, description;
    View signup;
    FirebaseAuth mAuth;
    FirebaseDatabase database;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_events);
        name = findViewById(R.id.name);
        date = findViewById(R.id.date_input);
        time = findViewById(R.id.time_input);
        date.setInputType(InputType.TYPE_NULL);
        time.setInputType(InputType.TYPE_NULL);
        neededNum = findViewById(R.id.needed);
        description = findViewById(R.id.description);
        signup = findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://togethermvp-57663-default-rtdb.firebaseio.com/");
        signup.setOnClickListener(this);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date);
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(time);
            }
        });
    }



    @Override
    public void onClick(View view) {
        if (view == signup){
            addEvent();
        }
    }

    public void addEvent(){
        String uid = mAuth.getCurrentUser().getUid();
        database.getReference("Users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User currentUser= snapshot.getValue(User.class);
                Event event = new Event(currentUser.getName(), name.getText().toString(), date.getText().toString(), currentUser.getLocation(), time.getText().toString(), description.getText().toString(), neededNum.getText().toString());
                String[] words = date.getText().toString().split("/");
                String word = String.join(":", words);
                database.getReference("Events").child(name.getText().toString()).setValue(event);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void showDateDialog(final EditText date_in) {
        final java.util.Calendar calendar= java.util.Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(java.util.Calendar.YEAR,year);
                calendar.set(java.util.Calendar.MONTH,month);
                calendar.set(java.util.Calendar.DAY_OF_MONTH,dayOfMonth);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("d/M/yyyy");
                date_in.setText(simpleDateFormat.format(calendar.getTime()));

            }
        };

        new DatePickerDialog(AddingEvents.this,dateSetListener,calendar.get(java.util.Calendar.YEAR),calendar.get(java.util.Calendar.MONTH),calendar.get(java.util.Calendar.DAY_OF_MONTH)).show();
    }

    private void showTimeDialog(final EditText time_in) {
        final java.util.Calendar calendar= java.util.Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(java.util.Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(java.util.Calendar.MINUTE,minute);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
                time_in.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        new TimePickerDialog(AddingEvents.this,timeSetListener,calendar.get(java.util.Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
    }
}