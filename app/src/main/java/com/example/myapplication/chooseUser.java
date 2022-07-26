package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class chooseUser extends AppCompatActivity {
    View student, worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
        student.setOnClickListener((View.OnClickListener) this);
        worker.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick(View view) {
        if(view == student) {
            Intent intent = new Intent(chooseUser.this, SignUpActivity.class);
            startActivity(intent);
        }
        else if(view == worker) {
            Intent intent = new Intent(chooseUser.this, ElderySignUp.class);
            startActivity(intent);
        }
    }
}