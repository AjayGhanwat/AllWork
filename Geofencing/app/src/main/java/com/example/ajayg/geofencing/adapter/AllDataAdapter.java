package com.example.ajayg.geofencing.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ajayg.geofencing.datamodel.AllData;
import com.example.ajayg.geofencing.R;

import java.util.ArrayList;

public class AllDataAdapter extends RecyclerView.Adapter<AllDataAdapter.viewholder> {

    ArrayList<AllData> list;

    public AllDataAdapter(ArrayList<AllData> list) {
        this.list = list;
    }

    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_data_row,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(viewholder holder, int position) {

        holder.date.setText(list.get(position).getDate());
        holder.enter.setText(list.get(position).getEnter());
        holder.exit.setText(list.get(position).getExit());
        holder.interval.setText(list.get(position).getInterval());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        TextView date,enter,exit,interval;

        public viewholder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.Date);
            enter = (TextView) itemView.findViewById(R.id.EnterTime);
            exit = (TextView) itemView.findViewById(R.id.ExitTime);
            interval = (TextView) itemView.findViewById(R.id.IntervalsAll);
        }
    }
}
