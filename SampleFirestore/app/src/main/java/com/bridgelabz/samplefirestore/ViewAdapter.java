package com.bridgelabz.samplefirestore;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.viewHolder> {

    ArrayList<userData> list;

    public ViewAdapter(ArrayList<userData> list) {
        this.list = list;
    }

    @Override
    public ViewAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewAdapter.viewHolder holder, int position) {

        holder.title.setText(list.get(position).getTitle());
        holder.desc.setText(list.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView title,desc;

        public viewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.displayTitle);
            desc = (TextView) itemView.findViewById(R.id.displayDesc);

        }
    }
}
