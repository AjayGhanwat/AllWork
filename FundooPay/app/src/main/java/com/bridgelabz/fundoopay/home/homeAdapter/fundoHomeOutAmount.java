package com.bridgelabz.fundoopay.home.homeAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bridgelabz.fundoopay.R;
import com.bridgelabz.fundoopay.home.model.outstandingAmounts;

import java.util.ArrayList;

public class fundoHomeOutAmount extends RecyclerView.Adapter<fundoHomeOutAmount.amountViewHolder> {

    ArrayList<outstandingAmounts> list;

    public fundoHomeOutAmount(ArrayList<outstandingAmounts> list) {
        this.list = list;
    }

    @Override
    public fundoHomeOutAmount.amountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.outstandingpayables_row, parent, false);

        return new amountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(fundoHomeOutAmount.amountViewHolder holder, int position) {

        holder.date.setText(list.get(position).getAmountDate());
        holder.time.setText(list.get(position).getAmountTime());
        holder.total.setText(list.get(position).getAmountTotal());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class amountViewHolder extends RecyclerView.ViewHolder{

        TextView date, time, total;

        public amountViewHolder(View itemView) {
            super(itemView);


            date = (TextView) itemView.findViewById(R.id.outstandingPayablesDate);
            time = (TextView) itemView.findViewById(R.id.outstandingPayablesTime);
            total = (TextView) itemView.findViewById(R.id.UserOutstandingPayables);
        }
    }
}
