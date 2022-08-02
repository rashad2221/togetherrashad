package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewElderFragment extends Fragment implements View.OnClickListener{
    EditText name, gender;
    DatabaseReference mDatabase;
    FirebaseDatabase database;
    String hobby;
    Button sign_up;
    RadioButton homework, chess, guitar, cooking, art, theater, writing, singing, reading;

    public NewElderFragment() {
    }

    public static NewElderFragment newInstance() {
        NewElderFragment fragment = new NewElderFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_elder, container, false);


        database = FirebaseDatabase.getInstance("https://togethermvp-57663-default-rtdb.firebaseio.com/");
        mDatabase = database.getReference("Elders");
        name = rootView.findViewById(R.id.name);
        gender = rootView.findViewById(R.id.gender);
        sign_up = rootView.findViewById(R.id.sign_up);
        sign_up.setOnClickListener(this);
        homework = rootView.findViewById(R.id.homework);
        homework.setOnClickListener(this);
        chess = rootView.findViewById(R.id.chess);
        chess.setOnClickListener(this);
        guitar = rootView.findViewById(R.id.play_guitar);
        guitar.setOnClickListener(this);
        cooking = rootView.findViewById(R.id.cooking);
        cooking.setOnClickListener(this);
        art = rootView.findViewById(R.id.art);
        art.setOnClickListener(this);
        theater = rootView.findViewById(R.id.theater);
        theater.setOnClickListener(this);
        singing = rootView.findViewById(R.id.singing);
        singing.setOnClickListener(this);
        reading = rootView.findViewById(R.id.reading);
        reading.setOnClickListener(this);

        return  rootView;

    }

    @Override
    public void onClick(View view) {
        if(view == sign_up) {
            Elder elder;
            elder = new Elder(name.getText().toString(),
                    gender.getText().toString(),
                    hobby);
            mDatabase.child(name.getText().toString()).setValue(elder);
        }
        if(view instanceof RadioButton){
            onRadioButtonClicked(view);
        }
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.homework:
                if (checked) {
                    hobby = "homework";
                }
                break;
            case R.id.chess:
                if (checked) {
                    hobby = "chess";
                }
                break;
            case R.id.play_guitar:
                if(checked) {
                    hobby = "play guitar";
                }
                break;
            case R.id.cooking:
                if(checked)
                    hobby = "cooking";
                break;
            case R.id.art:
                if(checked)
                    hobby = "art";
                break;
            case R.id.theater:
                if(checked)
                    hobby = "theater";
                break;
            case R.id.singing:
                if(checked)
                    hobby = "singing";
                break;
            case R.id.reading:
                if(checked)
                    hobby = "reading";
                break;
        }
    }
}