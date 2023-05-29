package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class NewEventFragment extends Fragment implements View.OnClickListener {

    EditText name, date, time, neededNum, description;
    Button add_elder, signup;
    FirebaseAuth mAuth;
    FirebaseDatabase database;

    public NewEventFragment() {
    }


    public static NewEventFragment newInstance() {
        NewEventFragment fragment = new NewEventFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_event, container, false);

        name = rootView.findViewById(R.id.name);
        date = rootView.findViewById(R.id.date_input);
        time = rootView.findViewById(R.id.time_input);
        date.setInputType(InputType.TYPE_NULL);
        time.setInputType(InputType.TYPE_NULL);
        neededNum = rootView.findViewById(R.id.needed);
        description = rootView.findViewById(R.id.description);
        signup = rootView.findViewById(R.id.signup1);
        add_elder = rootView.findViewById(R.id.add_elder);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://togethermvp-57663-default-rtdb.firebaseio.com/");
        signup.setOnClickListener(this);
        add_elder.setOnClickListener(this);

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


        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view == signup){
            addEvent();
        }
        else if (view == add_elder) {
            Intent i = new Intent(getActivity(), AddElder.class);
            startActivity(i);
        }
    }

    public void addEvent(){
        String uid = mAuth.getCurrentUser().getUid();
        database.getReference("Users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User currentUser= snapshot.getValue(User.class);
                Event event = new Event(currentUser.getName(), name.getText().toString(), date.getText().toString(), currentUser.getLocation(), currentUser.getAddress(), time.getText().toString(), description.getText().toString(), neededNum.getText().toString());
                String[] words = date.getText().toString().split("/");
                String word = String.join(":", words);
                database.getReference("Events").child(name.getText().toString()).setValue(event);
                Intent intent = new Intent(getActivity(), NavigationBarElder.class);
                startActivity(intent);
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

        new DatePickerDialog(getActivity(),dateSetListener,calendar.get(java.util.Calendar.YEAR),calendar.get(java.util.Calendar.MONTH),calendar.get(java.util.Calendar.DAY_OF_MONTH)).show();
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

        new TimePickerDialog(getActivity(),timeSetListener,calendar.get(java.util.Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
    }
}