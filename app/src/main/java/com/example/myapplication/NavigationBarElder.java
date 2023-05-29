package com.example.myapplication;

import android.os.Bundle;

import com.example.myapplication.databinding.ActivityNavigationBarElderBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NavigationBarElder extends AppCompatActivity {

    ActivityNavigationBarElderBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavigationBarElderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment1(new CalendarFragment());

        binding.bottomNavigationView2.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.calendarElder:
                    replaceFragment1(new CalendarFragment());
                    break;
                case R.id.newEvent:
                    replaceFragment(new NewEventFragment());
                    break;
                case R.id.newElder:
                    replaceFragment2(new NewElderFragment());
                    break;
                case R.id.viewElder:
                    replaceFragment3(new ListFragment());
                    break;
            }


            return true;
        });
    }

    private void replaceFragment(NewEventFragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout2, fragment);
        fragmentTransaction.commit();
    }

    private void replaceFragment1(CalendarFragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout2, fragment);
        fragmentTransaction.commit();
    }

    private void replaceFragment2(NewElderFragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout2, fragment);
        fragmentTransaction.commit();
    }

    private void replaceFragment3(ListFragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout2, fragment);
        fragmentTransaction.commit();
    }
}
