package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    EditText location, idNum, birthday;
    Spinner spinner;
    View signup;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://togethermvp-57663-default-rtdb.firebaseio.com/");
        mDatabase = database.getReference("Users");

        signup = findViewById(R.id.signup1);
        idNum = findViewById(R.id.idNum);
        birthday = findViewById(R.id.birthday);

        spinner = findViewById(R.id.location);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.locations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        String UserEmail = this.getIntent().getStringExtra("email");
        String UserName = this.getIntent().getStringExtra("username");
        String UserPassword = this.getIntent().getStringExtra("password");
        String UserPhone = this.getIntent().getStringExtra("phoneNum");


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount(UserEmail, UserPassword, UserName, UserPhone,
                        spinner.getSelectedItem().toString(),
                        idNum.getText().toString(),
                        birthday.getText().toString());
            }
        });
    }

    public void CreateAccount(String email, String password, String name, String phone, String location, String ID, String birthday){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information

                        User user = new User(name, email,password, location, ID, phone, birthday);
                        String uid = mAuth.getCurrentUser().getUid();
                        mDatabase.child(uid).setValue(user);
                        Intent intent = new Intent(SignUpActivity2.this, Calendar.class);
                        startActivity(intent);
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(SignUpActivity2.this, task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}