package com.bridgelabz.fundoopay.home.homeAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bridgelabz.fundoopay.R;
import com.bridgelabz.fundoopay.home.model.notifications;

import java.util.ArrayList;

public class notificationAdapter extends RecyclerView.Adapter<notificationAdapter.notificationViewHolder>{

    ArrayList<notifications> list;

    public notificationAdapter(ArrayList<notifications> list) {
        this.list = list;
    }


    @Override
    public notificationAdapter.notificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new notificationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_row, parent, false));
    }

    @Override
    public void onBindViewHolder(notificationAdapter.notificationViewHolder holder, int position) {

        holder.title.setText(list.get(position).getNotificationTitle());
        holder.date.setText(list.get(position).getNotificationDate());
        holder.time.setText(list.get(position).getNotificationTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class notificationViewHolder extends RecyclerView.ViewHolder{

        TextView title,date,time;

        public notificationViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.notificationTitle);
            date = (TextView) itemView.findViewById(R.id.notificationDate);
            time = (TextView) itemView.findViewById(R.id.notificationTime);
        }
    }
}
