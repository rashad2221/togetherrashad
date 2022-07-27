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
import com.google.firebase.database.FirebaseDatabase;

public class ElderySignUp extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    EditText username, password, email, location, phoneNum, address;
    Spinner spinner;
    View signup;
    FirebaseAuth mAuth;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eldery_sign_up);
        username = findViewById(R.id.name);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        phoneNum = findViewById(R.id.PhoneNum);
        signup = findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://togethermvp-57663-default-rtdb.firebaseio.com/");
        signup.setOnClickListener(this);

        spinner = findViewById(R.id.location);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.locations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View view) {
        if(view == signup){
            createAccount(email.getText().toString(), password.getText().toString() );
        }
    }

    public void createAccount(String email, String password) {
        if(email != null && password != null) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                User user = new User(username.getText().toString(), email, password, spinner.getSelectedItem().toString(), "0", phoneNum.getText().toString(),"1/1/1999", false, address.getText().toString());
                                String uid = mAuth.getCurrentUser().getUid();
                                database.getReference("Users").child(uid).setValue(user);
                                Intent intent = new Intent(ElderySignUp.this, AddingEvents.class);
                                startActivity(intent);
                            }
                            else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(ElderySignUp.this, task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

}