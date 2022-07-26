package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity2 extends AppCompatActivity  {
    EditText location, idNum, birthday;
    View signup;
    FirebaseAuth mAuth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://together-63e15-default-rtdb.europe-west1.firebasedatabase.app/");

        signup = findViewById(R.id.signup1);
        location = findViewById(R.id.location);
        idNum = findViewById(R.id.idNum);
        birthday = findViewById(R.id.birthday);

        String UserEmail = this.getIntent().getStringExtra("email");
        String UserName = this.getIntent().getStringExtra("username");
        String UserPassword = this.getIntent().getStringExtra("password");
        String UserPhone = this.getIntent().getStringExtra("phoneNum");


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount(UserEmail, UserPassword, UserName, UserPhone,
                        location.getText().toString(),
                        idNum.getText().toString(),
                        birthday.getText().toString());
                Intent intent = new Intent(SignUpActivity2.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }

    public void CreateAccount(String email, String password, String name, String phone, String location, String ID, String birthday){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                                User user = new User(name, email, password, location, ID, phone, birthday);
                                String uid = mAuth.getCurrentUser().getUid();
                                database.getReference("Users").child(uid).setValue(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUpActivity2.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void createAccount(String email, String password,
                              String phoneNum, String location,
                              String idNum,String UserName, String birthday) {
        if (email != null && password != null
                && phoneNum != null && location != null
                && idNum != null && birthday != null && UserName != null) {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //    public User(String name, String email, String password, String location, String idNum, String phoneNum, String birthday)
                                User user = new User (UserName, email,
                                        password,location, idNum,phoneNum,birthday );
                                String uid = mAuth.getCurrentUser().getUid();
                                database.getReference("Users").child(uid).setValue(user);
                                // Sign in success, update UI with the signed-in user's information

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignUpActivity2.this, task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }
    }
}