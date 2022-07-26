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

public class SignUpActivity2 extends AppCompatActivity implements View.OnClickListener {
    EditText location, idNum, birthday;
    View signup;
    FirebaseAuth mAuth;
    FirebaseDatabase database;

    String UserEmail, UserPassword, UserName, UserPhone;

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

        if (this.getIntent().getStringExtra("email") != null
                && this.getIntent().getStringExtra("username") != null
                && this.getIntent().getStringExtra("password") != null
                && this.getIntent().getStringExtra("phoneumber") != null) {
            UserEmail = (this.getIntent().getStringExtra("email"));
            UserName = (this.getIntent().getStringExtra("username"));
            UserPassword = (this.getIntent().getStringExtra("password"));
            UserPhone = (this.getIntent().getStringExtra("phonenumber"));

        } else {
            // mSystemView.setText("NO VALUE PASSED");
        }

        signup.setOnClickListener(this);
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
                                User user = new User (UserName, email,
                                        password,location, idNum,phoneNum,birthday );
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
    public void onClick(View view) {
        if(view == signup){
            createAccount(UserEmail, UserPassword, UserPhone, location.getText().toString(), idNum.getText().toString(),UserName, birthday.getText().toString());
            Intent intent = new Intent(SignUpActivity2.this, SignInActivity.class);
            startActivity(intent);


        }
    }


}