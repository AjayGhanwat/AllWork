package com.bridgelabz.fundoopay.home.view;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bridgelabz.fundoopay.MainActivity;
import com.bridgelabz.fundoopay.R;
import com.bridgelabz.fundoopay.base.BaseFragment;

public class userBankAccount extends BaseFragment implements View.OnClickListener{

    View v;

    Toolbar mainToolbar;
    TextView fundoo,pay,addAccount;
    ImageButton navigationIcon;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        return inflater.inflate(R.layout.activity_user_bank_accounts, container, false);
    }
    Fragment fragment = null;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v = view;
        initView();
        clickListner();
        initToolbarView();

        navigationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {

                getActivity().getFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initView() {

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

        addAccount.setVisibility(View.VISIBLE);
        navigationIcon.setVisibility(View.VISIBLE);
        fundoo.setVisibility(View.GONE);
        pay.setVisibility(View.GONE);
        mainToolbar.setVisibility(View.VISIBLE);
        addAccount.setText("Bank Accounts");

        super.onResume();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser){

            addAccount.setVisibility(View.VISIBLE);
            navigationIcon.setVisibility(View.VISIBLE);
            fundoo.setVisibility(View.GONE);
            pay.setVisibility(View.GONE);
            mainToolbar.setVisibility(View.VISIBLE);
            addAccount.setText("Bank Accounts");

        }
    }
}
