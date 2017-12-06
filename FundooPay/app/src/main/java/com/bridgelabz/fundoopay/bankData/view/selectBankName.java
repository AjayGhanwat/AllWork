package com.bridgelabz.fundoopay.bankData.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bridgelabz.fundoopay.R;
import com.bridgelabz.fundoopay.bankData.banknameadapter.recyclerBankNameAdapter;
import com.bridgelabz.fundoopay.base.BaseFragment;

import java.util.ArrayList;

public class selectBankName extends BaseFragment {

    View v;
    RecyclerView mRecyclerView;
    ArrayList<String> bankNames;
    recyclerBankNameAdapter adapter;

    Toolbar mainToolbar;
    TextView fundoo, pay, addAccount;
    ImageButton navigationIcon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        return inflater.inflate(R.layout.activity_select_bank_name, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v = view;

        initView();
        clickListner();
        initToolbarView();

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);

    }

    @Override
    public void initToolbarView() {
        mainToolbar = (Toolbar) v.findViewById(R.id.toolbar);

        fundoo = (TextView) v.findViewById(R.id.fundoo);
        pay = (TextView) v.findViewById(R.id.pay);
        addAccount = (TextView) v.findViewById(R.id.addAccount);
        navigationIcon = (ImageButton) v.findViewById(R.id.navigationicon);

    }

    @Override
    public void clickListner() {

    }

    @Override
    public void onResume() {

        mainToolbar.setTitle("");
        addAccount.setVisibility(View.GONE);
        navigationIcon.setVisibility(View.GONE);
        fundoo.setVisibility(View.VISIBLE);
        pay.setVisibility(View.VISIBLE);
        mainToolbar.setVisibility(View.VISIBLE);

        super.onResume();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            mainToolbar.setTitle("");
            addAccount.setVisibility(View.GONE);
            navigationIcon.setVisibility(View.GONE);
            fundoo.setVisibility(View.VISIBLE);
            pay.setVisibility(View.VISIBLE);
            mainToolbar.setVisibility(View.VISIBLE);

        }
    }
}
