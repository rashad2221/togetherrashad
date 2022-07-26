package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class chooseUser extends AppCompatActivity implements View.OnClickListener {
    Button student, worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
        student = findViewById(R.id.student);
        student.setOnClickListener(this);
        worker = findViewById(R.id.worker);
        worker.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if(v == student) {
            Intent intent = new Intent(chooseUser.this, SignUpActivity.class);
            startActivity(intent);
        }
        else if(v == worker) {
            Intent intent = new Intent(chooseUser.this, ElderySignUp.class);
            startActivity(intent);
        }

    }
}