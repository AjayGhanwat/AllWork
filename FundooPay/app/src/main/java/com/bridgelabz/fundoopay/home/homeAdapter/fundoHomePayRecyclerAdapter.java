package com.bridgelabz.fundoopay.home.homeAdapter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bridgelabz.fundoopay.R;
import com.bridgelabz.fundoopay.home.model.homeBills;
import com.bridgelabz.fundoopay.home.model.homePayables;
import com.bridgelabz.fundoopay.home.view.fundooPayHomeOustanding;
import com.bridgelabz.fundoopay.home.view.userBankAccount;

import java.util.ArrayList;

public class fundoHomePayRecyclerAdapter extends RecyclerView.Adapter<fundoHomePayRecyclerAdapter.billViewHolder>{

    ArrayList<homePayables> list;

    public fundoHomePayRecyclerAdapter(ArrayList<homePayables> list) {
        this.list = list;
    }

    @Override
    public fundoHomePayRecyclerAdapter.billViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payables_row,parent,false);
        return new billViewHolder(view);
    }

    @Override
    public void onBindViewHolder(fundoHomePayRecyclerAdapter.billViewHolder holder, int position) {

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

            marchandeName = (TextView) itemView.findViewById(R.id.pMarchandeName);
            marchandeLocation = (TextView)  itemView.findViewById(R.id.pMarchandeLocation);
            marchandePhoneNumber = (TextView) itemView.findViewById(R.id.pMarchandePhoneNumber);
            marchandeAddress = (TextView) itemView.findViewById(R.id.pMarchandeAddress);
            usersbill = (TextView) itemView.findViewById(R.id.pUserBill);

            acceptBill = (Button) itemView.findViewById(R.id.payBills);

            acceptBill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String mName = list.get(getAdapterPosition()).getMurchandName();
                    String mLocation = list.get(getAdapterPosition()).getShopeName();
                    String mAddress = list.get(getAdapterPosition()).getMurchandAddress();
                    String mPhone = list.get(getAdapterPosition()).getMobileNumber();
                    String mPrice = list.get(getAdapterPosition()).getTotalPrice();

                    Fragment fragment = new fundooPayHomeOustanding();

                    Bundle bundle=new Bundle();
                    bundle.putString("name",mName);
                    bundle.putString("location",mLocation);
                    bundle.putString("address",mAddress);
                    bundle.putString("phone",mPhone);
                    bundle.putString("price",mPrice);
                    fragment.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = ((Activity)itemView.getContext()).getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.mainactivityPanel, fragment).addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }
    }
}
