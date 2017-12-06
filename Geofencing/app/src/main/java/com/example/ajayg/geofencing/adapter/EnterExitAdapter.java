package com.example.ajayg.geofencing.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ajayg.geofencing.datamodel.EnterExit;
import com.example.ajayg.geofencing.R;

import java.util.ArrayList;

public class EnterExitAdapter extends RecyclerView.Adapter<EnterExitAdapter.viewholder> {

    ArrayList<EnterExit> list;

    public EnterExitAdapter(ArrayList<EnterExit> list) {
        this.list = list;
    }

    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.enter_exit_row,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(viewholder holder, int position) {

        holder.enter.setText(list.get(position).getEnter());
        holder.exit.setText(list.get(position).getExit());
        holder.interval.setText(list.get(position).getInterval());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        TextView enter,exit,interval;

        public viewholder(View itemView) {
            super(itemView);

            enter = (TextView) itemView.findViewById(R.id.enter);
            exit = (TextView) itemView.findViewById(R.id.exit);
            interval = (TextView) itemView.findViewById(R.id.interval);
        }
    }
}
