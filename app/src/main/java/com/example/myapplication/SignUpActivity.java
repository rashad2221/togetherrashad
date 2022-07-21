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
    EditText username, password, email, location, idNum, phoneNum, birthday;
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
        location = findViewById(R.id.location);
        idNum = findViewById(R.id.idNum);
        phoneNum = findViewById(R.id.PhoneNum);
        birthday = findViewById(R.id.birthday);
        signup = findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://together-63e15-default-rtdb.europe-west1.firebasedatabase.app/");

        User user = new User(username.getText().toString(), email.getText().toString(), password.getText().toString(), location.getText().toString(), idNum.getText().toString(), phoneNum.getText().toString(), birthday.getText().toString());
        String uid = mAuth.getCurrentUser().getUid();
        database.getReference("Users").child(uid).setValue(user);

        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == signup){
            createAccount(email.getText().toString(), password.getText().toString() );
        }
    }

    public void createAccount(String email, String password) {
        if(email != null && password != null) {
            Toast.makeText(this, "works h", Toast.LENGTH_LONG).show();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        }

}