package com.bridgelabz.fundoopay.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bridgelabz.fundoopay.MainActivity;
import com.bridgelabz.fundoopay.R;
import com.bridgelabz.fundoopay.adapter.recyclerBankNameAdapter;
import com.bridgelabz.fundoopay.base.BaseFragment;

import java.util.ArrayList;

public class selectBankName extends BaseFragment{

    View v;
    RecyclerView mRecyclerView;
    ArrayList<String> bankNames;
    recyclerBankNameAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_select_bank_name, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v = view;

        initView();
        clickListner();

        MainActivity.mainToolbar.setVisibility(View.VISIBLE);

        MainActivity.mainToolbar.setTitle("");

        bankNames.add("Allahabad Bank");
        bankNames.add("Andhra Bank");
        bankNames.add("Axis Bank");
        bankNames.add("Bank of Bahrain and Kuwait");
        bankNames.add("Bank of Baroda - Corporate Banking");
        bankNames.add("Bank of Baroda - Retail Banking");
        bankNames.add("Bank of India");
        bankNames.add("Bank of Maharashtra");
        bankNames.add("Canara Bank");
        bankNames.add("Central Bank of India");
        bankNames.add("City Union Bank");
        bankNames.add("Corporation Bank");
        bankNames.add("Deutsche Bank");
        bankNames.add("Development Credit Bank");
        bankNames.add("Dhanlaxmi Bank");
        bankNames.add("Federal Bank");
        bankNames.add("ICICI Bank");
        bankNames.add("IDBI Bank");
        bankNames.add("Indian Bank");
        bankNames.add("Indian Overseas Bank");
        bankNames.add("IndusInd Bank");
        bankNames.add("ING Vysya Bank");
        bankNames.add("Jammu and Kashmir Bank");
        bankNames.add("Karnataka Bank Ltd");
        bankNames.add("Karur Vysya Bank");
        bankNames.add("Kotak Bank");
        bankNames.add("Laxmi Vilas Bank");
        bankNames.add("Oriental Bank of Commerce");
        bankNames.add("Punjab National Bank - Corporate Banking");
        bankNames.add("Punjab National Bank - Retail Banking");
        bankNames.add("Punjab & Sind Bank");
        bankNames.add("Shamrao Vitthal Co-operative Bank");
        bankNames.add("South Indian Bank");
        bankNames.add("State Bank of Bikaner & Jaipur");
        bankNames.add("State Bank of Hyderabad");
        bankNames.add("State Bank of India");
        bankNames.add("State Bank of Mysore");
        bankNames.add("State Bank of Patiala");
        bankNames.add("State Bank of Travancore");
        bankNames.add("Syndicate Bank");
        bankNames.add("Tamilnad Mercantile Bank Ltd.");
        bankNames.add("UCO Bank");
        bankNames.add("Union Bank of India");
        bankNames.add("United Bank of India");
        bankNames.add("Vijaya Bank");
        bankNames.add("Yes Bank Ltd");

        adapter = new recyclerBankNameAdapter(bankNames);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void initView() {

        bankNames = new ArrayList<>();

        mRecyclerView = (RecyclerView) v.findViewById(R.id.bankListRecycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void clickListner() {

    }
}
