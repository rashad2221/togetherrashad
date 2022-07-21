package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class EventArrayAdapter extends ArrayAdapter<Event>{

    private Context context;
    private int resource;


    public EventArrayAdapter(@NonNull Context context, int resource, ArrayList<Event> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(this.context).inflate(this.resource, parent, false);
        }
        // Code goes here
        Event event = getItem(position);
        if(event != null) {
            TextView nameTextView = convertView.findViewById(R.id.name);
            TextView locationTextView = convertView.findViewById(R.id.location);
            TextView dateTextView = convertView.findViewById(R.id.date);
            TextView timeTextView = convertView.findViewById(R.id.time);
            TextView descriptionTextView = convertView.findViewById(R.id.description);
            nameTextView.setText(event.getName());
            locationTextView.setText(event.getLocation());
            dateTextView.setText(event.getDate());
            timeTextView.setText(event.getTime());
            descriptionTextView.setText(event.getDescription());
        }
        return convertView;

    }
}
