package com.bridgelabz.googlekeepdemo.archive.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bridgelabz.googlekeepdemo.R;
import com.bridgelabz.googlekeepdemo.archive.model.archiveData;

import java.util.ArrayList;

public class archiveAdapter extends RecyclerView.Adapter<archiveAdapter.viewHolder> {

    ArrayList<archiveData> list;

    public archiveAdapter(ArrayList<archiveData> list) {
        this.list = list;
    }

    @Override
    public archiveAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_row, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(archiveAdapter.viewHolder holder, int position) {
        holder.user_Title.setText(list.get(position).getTitle());
        holder.user_desc.setText(list.get(position).getNote());
        holder.imageView.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView user_Title;
        TextView user_desc;
        AppCompatImageView imageView;
        CardView card;

        public viewHolder(View itemView) {
            super(itemView);

            user_Title = (TextView) itemView.findViewById(R.id.titleTextView);
            user_desc = (TextView) itemView.findViewById(R.id.descTextView);
            imageView = (AppCompatImageView) itemView.findViewById(R.id.importantPin);
            card = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
}
