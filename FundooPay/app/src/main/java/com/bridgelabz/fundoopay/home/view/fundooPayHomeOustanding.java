package com.bridgelabz.fundoopay.home.view;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bridgelabz.fundoopay.R;
import com.bridgelabz.fundoopay.base.BaseFragment;
import com.bridgelabz.fundoopay.home.homeAdapter.fundoHomeBillRecyclerAdapter;
import com.bridgelabz.fundoopay.home.homeAdapter.fundoHomeOutAmount;
import com.bridgelabz.fundoopay.home.homeAdapter.fundoHomeOutBill;
import com.bridgelabz.fundoopay.home.homeAdapter.fundoHomePayRecyclerAdapter;
import com.bridgelabz.fundoopay.home.model.homeBills;
import com.bridgelabz.fundoopay.home.model.homePayables;
import com.bridgelabz.fundoopay.home.model.outstandingAmounts;
import com.bridgelabz.fundoopay.home.model.outstandingBills;

import java.util.ArrayList;

public class fundooPayHomeOustanding extends BaseFragment implements View.OnClickListener {

    Fragment fragment;
    View v;
    RecyclerView mainRecyclerView;
    AppCompatButton billsOutstandins, payablesOutstandins;
    AppCompatButton billsUnPaid, billsPaid;

    Button payOut;

    Toolbar mainToolbar;
    TextView fundoo, pay, addAccount;
    ImageButton navigationIcon;

    String mName;
    String mLocation;
    String mAddress;
    String mPhone;
    String mPrice;

    TextView oMarchandeName, oMarchandeLocation,oMarchandePhone, oMarchandeAddress,oMarchandePrice;

    outstandingBills outstandingBills;
    ArrayList<outstandingBills> mOutstandingBills;
    RecyclerView.Adapter outstandingBillAdapter;


    outstandingAmounts outstandingAmounts;
    ArrayList<outstandingAmounts> mOutstandingAmounts;
    RecyclerView.Adapter outstandingAmountAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View viewUser = inflater.inflate(R.layout.activity_oustandings, container, false);
        setHasOptionsMenu(true);

        mName = getArguments().getString("name");
        mLocation = getArguments().getString("location");
        mAddress = getArguments().getString("address");
        mPhone = getArguments().getString("phone");
        mPrice = getArguments().getString("price");
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

        oMarchandeName.setText(mName);
        oMarchandeLocation.setText(mLocation);
        oMarchandePhone.setText(mPhone);
        oMarchandeAddress.setText(mAddress);
        oMarchandePrice.setText(mPrice);

        getOutstandingBills();

    }

    @Override
    public void initView() {

        mainRecyclerView = (RecyclerView) v.findViewById(R.id.OutstandingBillsRe);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mainRecyclerView.setHasFixedSize(true);

        billsOutstandins = (AppCompatButton) v.findViewById(R.id.BillsOutstanding);
        payablesOutstandins = (AppCompatButton) v.findViewById(R.id.payablesOutstanding);

        billsUnPaid = (AppCompatButton) v.findViewById(R.id.BillsUnPaid);
        billsPaid = (AppCompatButton) v.findViewById(R.id.billsPaid);

        payOut = (Button) v.findViewById(R.id.payOutBills);

        oMarchandeName = (TextView) v.findViewById(R.id.oMarchandeName);
        oMarchandeLocation = (TextView) v.findViewById(R.id.oMarchandeLocation);
        oMarchandePhone = (TextView) v.findViewById(R.id.oMarchandePhoneNumber);
        oMarchandeAddress = (TextView) v.findViewById(R.id.oMarchandeAddress);
        oMarchandePrice = (TextView) v.findViewById(R.id.userOutBill);

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

        billsOutstandins.setOnClickListener(this);
        payablesOutstandins.setOnClickListener(this);

        billsUnPaid.setOnClickListener(this);
        billsPaid.setOnClickListener(this);

        payOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.BillsOutstanding:

                billsOutstandins.setBackground(getResources().getDrawable(R.drawable.bill_first));
                payablesOutstandins.setBackground(getResources().getDrawable(R.drawable.payables_first));

                getOutstandingBills();

                break;

            case R.id.payablesOutstanding:

                billsOutstandins.setBackground(getResources().getDrawable(R.drawable.bills_sec));
                payablesOutstandins.setBackground(getResources().getDrawable(R.drawable.payables_sec));

                getOutstandingAmount();

                break;

            case R.id.BillsUnPaid:

                billsUnPaid.setBackground(getResources().getDrawable(R.drawable.bill_first));
                billsPaid.setBackground(getResources().getDrawable(R.drawable.payables_first));

                break;

            case R.id.billsPaid:

                billsUnPaid.setBackground(getResources().getDrawable(R.drawable.bills_sec));
                billsPaid.setBackground(getResources().getDrawable(R.drawable.payables_sec));

                break;

            case R.id.payOutBills :

                Toast.makeText(getActivity(), "Pay Outstandings!!", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    private void getOutstandingBills() {

        mOutstandingBills = new ArrayList<>();

        outstandingBills = new outstandingBills("21, July 2017", "2:15 PM", "334");
        mOutstandingBills.add(outstandingBills);

        outstandingBills = new outstandingBills("21, July 2017", "2:15 PM", "334");
        mOutstandingBills.add(outstandingBills);

        outstandingBills = new outstandingBills("21, July 2017", "2:15 PM", "334");
        mOutstandingBills.add(outstandingBills);

        outstandingBillAdapter = new fundoHomeOutBill(mOutstandingBills);

        mainRecyclerView.setAdapter(outstandingBillAdapter);

        outstandingBillAdapter.notifyDataSetChanged();

    }

    private void getOutstandingAmount() {

        mOutstandingAmounts = new ArrayList<>();

        outstandingAmounts = new outstandingAmounts("21, July 2017", "2:15 PM", "200");
        mOutstandingAmounts.add(outstandingAmounts);

        outstandingAmounts = new outstandingAmounts("21, July 2017", "2:15 PM", "200");
        mOutstandingAmounts.add(outstandingAmounts);

        outstandingAmounts = new outstandingAmounts("21, July 2017", "2:15 PM", "200");
        mOutstandingAmounts.add(outstandingAmounts);

        outstandingAmountAdapter = new fundoHomeOutAmount(mOutstandingAmounts);

        mainRecyclerView.setAdapter(outstandingAmountAdapter);

        outstandingAmountAdapter.notifyDataSetChanged();


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
