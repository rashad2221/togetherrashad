package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username, password, email, phoneNum;
    View signup;
    FirebaseAuth mAuth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        phoneNum = findViewById(R.id.PhoneNum);
        signup = findViewById(R.id.next);
        //mAuth = FirebaseAuth.getInstance();
        //database = FirebaseDatabase.getInstance("https://together-63e15-default-rtdb.europe-west1.firebasedatabase.app/");


        signup.setOnClickListener(this);

    }

    public void onClick(View view) {
        if(view == signup) {
            Intent i = new Intent(this, SignUpActivity2.class);
            String name = username.getText().toString();
            i.putExtra("username", name);
            i.putExtra("password", password.getText().toString());
            i.putExtra("email", email.getText().toString());
            i.putExtra("phoneNum", phoneNum.getText().toString());
            startActivity(i);
        }

    }


    public void SendInfo(String email, String password) {
        if(email != null && password != null) {
            Toast.makeText(this, "works h", Toast.LENGTH_LONG).show();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                User user = new User(username.getText().toString(),
                                        email,
                                        password,
                                        phoneNum.getText().toString());

                                String uid = mAuth.getCurrentUser().getUid();
                                database.getReference("Users").child(uid).setValue(user);
                                Intent intent = new Intent(SignUpActivity.this, SignUpActivity2.class);
                                startActivity(intent);
                            }
                            else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(SignUpActivity.this, task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
        }

}