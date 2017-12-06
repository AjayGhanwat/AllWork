package com.example.ajayg.sqlitedatabaseexample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ViewHolder extends RecyclerView.Adapter<ViewHolder.userViewHolder> {

    ArrayList<DataModel> data;

    public ViewHolder(ArrayList<DataModel> data) {
        this.data = data;
    }

    @Override
    public userViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carview, parent, false);
        return new userViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(userViewHolder holder, int position) {

        holder.title.setText(data.get(position).getTitle());
        holder.desc.setText(data.get(position).getDesc());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class userViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView title, desc;

        public userViewHolder(final View itemView) {
            super(itemView);

            title = (AppCompatTextView) itemView.findViewById(R.id.titletextView);
            desc = (AppCompatTextView) itemView.findViewById(R.id.desctextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), EditData.class);
                    intent.putExtra("id", data.get(getAdapterPosition()).getId());
                    intent.putExtra("title", data.get(getAdapterPosition()).getTitle());
                    intent.putExtra("desc", data.get(getAdapterPosition()).getDesc());
                    itemView.getContext().startActivity(intent);
                }
            });
        }

    }
}
