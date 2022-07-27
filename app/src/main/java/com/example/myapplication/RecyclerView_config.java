package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_config {
    private Context mContext;
    private EventAdapter mEventAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Event> events, List<String> keys) {
        mContext = context;
        mEventAdapter = new EventAdapter(events, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mEventAdapter);
    }


    class EventItemView extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView date;
        private TextView time;
        private TextView location;
        private TextView description;

        private String key;

        public EventItemView(ViewGroup parant) {
            super(LayoutInflater.from(mContext).inflate(R.layout.event_list_item, parant, false));

            title = (TextView) itemView.findViewById(R.id.title_text_view);
            date = (TextView) itemView.findViewById(R.id.date);
            time = (TextView) itemView.findViewById(R.id.time);
            location = (TextView) itemView.findViewById(R.id.location);
            description = (TextView) itemView.findViewById(R.id.description);
        }

        public void Bind(Event event, String key) {
            title.setText(event.getName());
            date.setText(event.getDate());
            time.setText(event.getTime());
            location.setText("Address: "+event.getLocation());
            description.setText(event.getDescription());
            this.key = key;
        }
    }

    class EventAdapter extends RecyclerView.Adapter<EventItemView> {
        private List<Event> mEventList;
        private List<String> mKeys;

        public EventAdapter(List<Event> mEventList, List<String> mKeys) {
            this.mEventList = mEventList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public EventItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new EventItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull EventItemView holder, int position) {
        holder.Bind(mEventList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mEventList.size();
        }
    }
}
