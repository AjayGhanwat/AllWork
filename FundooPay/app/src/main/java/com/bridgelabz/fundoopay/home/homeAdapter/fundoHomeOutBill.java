package com.bridgelabz.fundoopay.home.homeAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bridgelabz.fundoopay.R;
import com.bridgelabz.fundoopay.home.model.outstandingBills;

import java.util.ArrayList;

public class fundoHomeOutBill extends RecyclerView.Adapter<fundoHomeOutBill.billViewHolder>{

    ArrayList<outstandingBills> list;

    public fundoHomeOutBill(ArrayList<outstandingBills> list) {
        this.list = list;
    }

    @Override
    public fundoHomeOutBill.billViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.outstandingbills_row,parent,false);

        return new billViewHolder(view);
    }

    @Override
    public void onBindViewHolder(fundoHomeOutBill.billViewHolder holder, int position) {

        holder.date.setText(list.get(position).getBillsDate());
        holder.time.setText(list.get(position).getBillsTime());
        holder.total.setText(list.get(position).getBillsTotal());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class billViewHolder extends RecyclerView.ViewHolder{

        TextView date, time, total;

        public billViewHolder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.outstandingBillDate);
            time = (TextView) itemView.findViewById(R.id.outstandingBillTime);
            total = (TextView) itemView.findViewById(R.id.UserOutstandingBill);

        }
    }
}

