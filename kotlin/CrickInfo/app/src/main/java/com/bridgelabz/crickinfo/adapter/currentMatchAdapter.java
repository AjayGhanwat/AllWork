package com.bridgelabz.crickinfo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bridgelabz.crickinfo.R;
import com.bridgelabz.crickinfo.model.matchDataModel;
import com.bridgelabz.crickinfo.model.scoreDataModel;
import com.bridgelabz.crickinfo.util.NewMatchesScore;

import java.util.ArrayList;

public class currentMatchAdapter extends RecyclerView.Adapter<currentMatchAdapter.viewHolder>{

    ArrayList<matchDataModel> list;
    Context context;

    public currentMatchAdapter(Context context,ArrayList<matchDataModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_card_row, parent, false);
        view.setMinimumWidth(parent.getMeasuredWidth());
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {

        holder.matchType.setText(list.get(position).getMatchType());
        holder.matchFTeam.setText(list.get(position).getMatchFirstTeam());
        holder.matchSTeam.setText(list.get(position).getMatchSecTeam());
        if (!list.get(position).getMatchToss().equals("null")) {
            holder.matchToss.setText(list.get(position).getMatchToss() + " Won The Toss");
        }else{
            holder.matchToss.setText("");
        }
        holder.matchTime.setText(list.get(position).getMatchTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView matchType,matchFTeam,matchSTeam,matchToss,matchTime;

        public viewHolder(View itemView) {
            super(itemView);

            matchType = itemView.findViewById(R.id.matchType);
            matchFTeam = itemView.findViewById(R.id.teamFirst);
            matchSTeam = itemView.findViewById(R.id.teamSec);
            matchToss = itemView.findViewById(R.id.matchToss);
            matchTime = itemView.findViewById(R.id.matchTime);
        }
    }
}
