package com.bridgelabz.fundoopay.home.view;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bridgelabz.fundoopay.R;
import com.bridgelabz.fundoopay.base.BaseFragment;
import com.bridgelabz.fundoopay.home.homeAdapter.fundoHomeBillRecyclerAdapter;
import com.bridgelabz.fundoopay.home.homeAdapter.fundoHomePayRecyclerAdapter;
import com.bridgelabz.fundoopay.home.model.homeBills;
import com.bridgelabz.fundoopay.home.model.homePayables;

import java.util.ArrayList;

public class fundooPayHome extends BaseFragment implements View.OnClickListener {

    Fragment fragment;
    View v;
    RecyclerView mainRecyclerView;
    AppCompatButton bills, payables;

    Toolbar mainToolbar;
    TextView fundoo, pay, addAccount;
    ImageButton navigationIcon;

    homeBills homeBills;
    ArrayList<homeBills> homeUserBills;
    RecyclerView.Adapter billAdapter;

    homePayables homePayables;
    ArrayList<homePayables> homeUserPayables;
    RecyclerView.Adapter PayablesAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View viewUser = inflater.inflate(R.layout.activity_fundoo_pay_home, container, false);
        setHasOptionsMenu(true);
        return viewUser;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.v = view;

        initView();
        clickListner();
        initToolbarView();

        displayBills();

    }

    @Override
    public void initView() {

        mainRecyclerView = (RecyclerView) v.findViewById(R.id.allBillsPayables);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mainRecyclerView.setHasFixedSize(true);

        bills = (AppCompatButton) v.findViewById(R.id.BillsUsers);
        payables = (AppCompatButton) v.findViewById(R.id.payablesUsers);

    }

    @Override
    public void initToolbarView() {
        mainToolbar = (Toolbar) v.findViewById(R.id.toolbar);

        fundoo = (TextView) v.findViewById(R.id.fundoo);
        pay = (TextView) v.findViewById(R.id.pay);
        addAccount = (TextView) v.findViewById(R.id.addAccount);
        navigationIcon = (ImageButton) v.findViewById(R.id.navigationicon);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mainToolbar);

    }

    @Override
    public void clickListner() {

        bills.setOnClickListener(this);
        payables.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.BillsUsers:

                bills.setBackground(getResources().getDrawable(R.drawable.bill_first));
                payables.setBackground(getResources().getDrawable(R.drawable.payables_first));

                displayBills();

                break;

            case R.id.payablesUsers:

                bills.setBackground(getResources().getDrawable(R.drawable.bills_sec));
                payables.setBackground(getResources().getDrawable(R.drawable.payables_sec));

                displayPayables();

                break;
        }
    }

    private void displayPayables() {

        homeUserPayables = new ArrayList<>();

        homePayables = new homePayables("NATARAJ", "Super Market", "9821772876", "801 Sai Samarth Business Park, Deonar Road,\n" +
                "Deonar, Mumbai, Maharashtra, 400088.", " 754");

        homeUserPayables.add(homePayables);

        homePayables = new homePayables("CleanNclear", "Laundry", "9821772876", "801 Sai Samarth Business Park, Deonar Road,\n" +
                "Deonar, Mumbai, Maharashtra, 400088.", " 90");

        homeUserPayables.add(homePayables);

        PayablesAdapter = new fundoHomePayRecyclerAdapter(homeUserPayables);

        mainRecyclerView.setAdapter(PayablesAdapter);
        PayablesAdapter.notifyDataSetChanged();

    }

    private void displayBills() {

        homeUserBills = new ArrayList<>();

        homeBills = new homeBills("NATARAJ", "Super Market", "9821772876", "801 Sai Samarth Business Park, Deonar Road,\n" +
                "Deonar, Mumbai, Maharashtra, 400088.", " 754");

        homeUserBills.add(homeBills);

        homeBills = new homeBills("CleanNclear", "Laundry", "9821772876", "801 Sai Samarth Business Park, Deonar Road,\n" +
                "Deonar, Mumbai, Maharashtra, 400088.", " 90");

        homeUserBills.add(homeBills);

        billAdapter = new fundoHomeBillRecyclerAdapter(homeUserBills);

        mainRecyclerView.setAdapter(billAdapter);
        billAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.bankAccouts:

                setHasOptionsMenu(false);

                fragment = new userBankAccount();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainactivityPanel, fragment).addToBackStack(null);
                fragmentTransaction.commit();

                break;

            case R.id.userNotifications:

                setHasOptionsMenu(false);

                fragment = new usersNotification();

                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainactivityPanel, fragment).addToBackStack(null);
                fragmentTransaction.commit();

                break;

            case R.id.userSettings:

                setHasOptionsMenu(false);

                fragment = new userSettings();

                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainactivityPanel, fragment).addToBackStack(null);
                fragmentTransaction.commit();

                break;

            case R.id.userLogout:

                Toast.makeText(getActivity(), "Log Out", Toast.LENGTH_SHORT).show();

                break;

        }

        return super.onOptionsItemSelected(item);
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
            addAccount.setVisibility(View.GONE);
            navigationIcon.setVisibility(View.GONE);
            fundoo.setVisibility(View.VISIBLE);
            pay.setVisibility(View.VISIBLE);
            mainToolbar.setVisibility(View.VISIBLE);

        }
    }
}
