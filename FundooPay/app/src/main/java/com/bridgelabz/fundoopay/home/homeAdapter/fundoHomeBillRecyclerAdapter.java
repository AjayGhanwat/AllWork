package com.bridgelabz.fundoopay.home.homeAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bridgelabz.fundoopay.R;
import com.bridgelabz.fundoopay.home.model.homeBills;

import java.util.ArrayList;

public class fundoHomeBillRecyclerAdapter extends RecyclerView.Adapter<fundoHomeBillRecyclerAdapter.billViewHolder>{

    ArrayList<homeBills> list;

    public fundoHomeBillRecyclerAdapter(ArrayList<homeBills> list) {
        this.list = list;
    }

    @Override
    public fundoHomeBillRecyclerAdapter.billViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bills_row,parent,false);
        return new billViewHolder(view);
    }

    @Override
    public void onBindViewHolder(fundoHomeBillRecyclerAdapter.billViewHolder holder, int position) {

        holder.marchandeName.setText(list.get(position).getMurchandName());
        holder.marchandeLocation.setText(list.get(position).getShopeName());
        holder.marchandePhoneNumber.setText(list.get(position).getMobileNumber());
        holder.marchandeAddress.setText(list.get(position).getMurchandAddress());
        holder.usersbill.setText(list.get(position).getTotalPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class billViewHolder extends RecyclerView.ViewHolder{

        TextView marchandeName,marchandeLocation, marchandeAddress, marchandePhoneNumber, usersbill;
        Button acceptBill;

        public billViewHolder(final View itemView) {
            super(itemView);

            marchandeName = (TextView) itemView.findViewById(R.id.bMarchandeName);
            marchandeLocation = (TextView)  itemView.findViewById(R.id.bMarchandeLocation);
            marchandePhoneNumber = (TextView) itemView.findViewById(R.id.bMarchandePhoneNumber);
            marchandeAddress = (TextView) itemView.findViewById(R.id.bMarchandeAddress);
            usersbill = (TextView) itemView.findViewById(R.id.bUserBill);

            acceptBill = (Button) itemView.findViewById(R.id.acceptBills);

            acceptBill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    list.remove(list.get(getAdapterPosition()));
                    notifyDataSetChanged();

                }
            });
        }
    }
}
