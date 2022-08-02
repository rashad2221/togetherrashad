package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    View signup;
    View sign_in;
    View about_us;
    View donate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = findViewById(R.id.signup);
        signup.setOnClickListener(this);
        sign_in = findViewById(R.id.sign_in);
        sign_in.setOnClickListener(this);
        about_us = findViewById(R.id.about);
        about_us.setOnClickListener(this);
//        donate = findViewById(R.id.donate);
//        donate.setOnClickListener(this);

        //change
    }

    @Override
    public void onClick(View view) {
        if (view == signup) {
            Intent intent = new Intent(MainActivity.this, chooseUser.class);
            startActivity(intent);
        } else if (view == sign_in) {
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
        } else if (view == about_us) {
            Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
            startActivity(intent);
        }
        else if( view == donate){
            Intent intent = new Intent(MainActivity.this, DonationActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "sign out", Toast.LENGTH_SHORT);
                Intent intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

