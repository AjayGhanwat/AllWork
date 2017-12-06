package com.bridgelabz.fundoopay.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bridgelabz.fundoopay.R;

import java.util.ArrayList;

public class recyclerBankNameAdapter extends RecyclerView.Adapter<recyclerBankNameAdapter.viewHolder>{

    ArrayList<String> list;

    public recyclerBankNameAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public recyclerBankNameAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bank_list_card_view, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(recyclerBankNameAdapter.viewHolder holder, int position) {

        holder.bankName.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView bankName;

        public viewHolder(View itemView) {
            super(itemView);

            bankName = (TextView) itemView.findViewById(R.id.bankName);
        }
    }
}
