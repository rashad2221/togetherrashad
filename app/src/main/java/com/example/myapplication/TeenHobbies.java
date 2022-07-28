package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TeenHobbies extends AppCompatActivity implements View.OnClickListener {
    EditText name, gender;
    View sign_up, homework, chess, play_guitar, cooking, art, theater, writing, singing;
    DatabaseReference mDatabase;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teen_hobbies);
        mDatabase = database.getReference("Elders");

        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        homework = findViewById(R.id.homework);
        chess = findViewById(R.id.chess);
        play_guitar = findViewById(R.id.play_guitar);
        cooking = findViewById(R.id.cooking);
        art = findViewById(R.id.art);
        theater = findViewById(R.id.theater);
        writing = findViewById(R.id.writing);
        singing = findViewById(R.id.singing);
        sign_up = findViewById(R.id.sign_up);

        homework.setOnClickListener(this);
        chess.setOnClickListener(this);
        play_guitar.setOnClickListener(this);
        cooking.setOnClickListener(this);
        art.setOnClickListener(this);
        theater.setOnClickListener(this);
        writing.setOnClickListener(this);
        singing.setOnClickListener(this);
        sign_up.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String hobby = "";
        if(view == homework) {
            hobby = "homework";
        }
        else if(view == chess) {
            hobby = "chess";
        }
        else if(view == play_guitar) {
            hobby = "play guitar";
        }
        else if(view == cooking) {
            hobby = "cooking";
        }
        else if(view == art) {
            hobby = "art";
        }
        else if(view == theater) {
            hobby = "theater";
        }
        else if(view == writing) {
            hobby = "writing";
        }
        else if(view == singing) {
            hobby = "singing";
        }
        else if(view == sign_up) {
            Elder elder;
            elder = new Elder(name.getText().toString(),
                    gender.getText().toString(),
                    hobby);
            mDatabase.child(name.getText().toString()).setValue(elder);
        }
    }
}