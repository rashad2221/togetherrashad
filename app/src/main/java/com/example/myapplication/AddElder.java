package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddElder extends AppCompatActivity implements View.OnClickListener {
    EditText name, gender;
    DatabaseReference mDatabase;
    FirebaseDatabase database;
    String hobby;
    Button sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_elder);
        database = FirebaseDatabase.getInstance("https://togethermvp-57663-default-rtdb.firebaseio.com/");
        mDatabase = database.getReference("Elders");
        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        sign_up = findViewById(R.id.sign_up);
        sign_up.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == sign_up) {
            Elder elder;
            elder = new Elder(name.getText().toString(),
                    gender.getText().toString(),
                    hobby);
            mDatabase.child(name.getText().toString()).setValue(elder);
        }
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.homework:
                if (checked) {
                    hobby = "homework";
                }
                break;
            case R.id.chess:
                if (checked) {
                    hobby = "chess";
                }
                break;
            case R.id.play_guitar:
                if(checked) {
                    hobby = "play guitar";
                }
                break;
            case R.id.cooking:
                if(checked)
                    hobby = "cooking";
                break;
            case R.id.art:
                if(checked)
                    hobby = "art";
                break;
            case R.id.theater:
                if(checked)
                    hobby = "theater";
                break;
            case R.id.singing:
                if(checked)
                    hobby = "singing";
                break;
            case R.id.reading:
                if(checked)
                    hobby = "reading";
                break;
        }
    }
}