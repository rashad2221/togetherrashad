package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContentActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    ListView usersListView;
    ArrayList<User> users;
    ArrayAdapter<User> arrayAdapter;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://meetdevworkshop-82ffa-default-rtdb.europe-west1.firebasedatabase.app/");
        users = new ArrayList<User>();
        User Yanai = new User("Yanai", "yanai0165@gmail.com", "12345678", "Ramla", "327521753", "0534323214", "17/01/05");
        String uid = mAuth.getCurrentUser().getUid();
        database.getReference("Users").child(uid).setValue(Yanai);

        // database.getReference("Users").child("2").setValue(user);
        DatabaseReference users_data = database.getReference();
        users_data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()) {
                    User user = data.getValue(User.class);
                    users.add(user);
                }
                // usersListView = findViewById(R.id.startupslistview);
                // arrayAdapter = new UserArrayAdapter(ContentActivity.this, R.layout.custom_row, users);
                // usersListView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // users.add(Yanai);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.sign_out:
                mAuth.signOut();
                // Toast.makeText(this, "sign out", Toast.LENGTH_SHORT);
                intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                break;
            case R.id.calendar:
                intent = new Intent(this, Calendar.class);
                startActivity(intent);
            // default:
                // return super.onOptionsItemSelected(item);
        }
        return true;
    }
}