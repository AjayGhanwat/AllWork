package com.bridgelabz.cardview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class userViewAdapter extends RecyclerView.Adapter<userViewAdapter.userViewHolder> {

    static String key;
    static String date;
    private ArrayList<Data> list;

    public userViewAdapter(ArrayList<Data> list) {

        this.list = list;
    }

    @Override
    public userViewAdapter.userViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_cardview, parent, false);
        return new userViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(userViewAdapter.userViewHolder holder, int position) {

        int color;

        key = list.get(position).getKey();
        color = list.get(position).getColor();
        date = list.get(position).getDate();

        String hexColor = String.format("#%06X", (0xFFFFFF & color));

        holder.user_Title.setText(list.get(position).getTitle());
        holder.user_desc.setText(list.get(position).getDesc());
        holder.card.setCardBackgroundColor(Color.parseColor(hexColor));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class userViewHolder extends RecyclerView.ViewHolder {
        TextView user_Title;
        TextView user_desc;

        CardView card;

        userViewHolder(View itemView) {
            super(itemView);
            user_Title = (TextView) itemView.findViewById(R.id.titleTextView);
            user_desc = (TextView) itemView.findViewById(R.id.descTextView);
            card = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
}
