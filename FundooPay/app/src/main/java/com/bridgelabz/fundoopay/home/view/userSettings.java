package com.bridgelabz.fundoopay.home.view;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bridgelabz.fundoopay.R;
import com.bridgelabz.fundoopay.base.BaseFragment;

public class userSettings extends BaseFragment implements View.OnClickListener {

    Fragment fragment;
    View v;
    LinearLayout phone, profile;
    TextView phoneTextView, profileTextView;
    Button saveUpdates;

    Toolbar mainToolbar;
    TextView fundoo, pay, addAccount;
    ImageButton navigationIcon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        return inflater.inflate(R.layout.activity_user_setting, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v = view;

        initView();
        clickListner();
        initToolbarView();

        navigationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View ve) {

                getActivity().getFragmentManager().popBackStack();

            }
        });
    }

    @Override
    public void initView() {

        phoneTextView = (TextView) v.findViewById(R.id.usersPhoneNumberEdit);
        profileTextView = (TextView) v.findViewById(R.id.usersProfileEdit);

        phone = (LinearLayout) v.findViewById(R.id.phoneNumberOldNew);
        profile = (LinearLayout) v.findViewById(R.id.profileUpdate);

        saveUpdates = (Button) v.findViewById(R.id.savewUpdates);

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

        phoneTextView.setOnClickListener(this);
        profileTextView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.usersPhoneNumberEdit:

                if (phone.getVisibility() == View.GONE) {
                    phone.setVisibility(View.VISIBLE);
                    saveUpdates.setVisibility(View.VISIBLE);
                } else if (phone.getVisibility() == View.VISIBLE && profile.getVisibility() == View.VISIBLE) {
                    phone.setVisibility(View.GONE);
                } else {
                    phone.setVisibility(View.GONE);
                    saveUpdates.setVisibility(View.GONE);
                }

                break;

            case R.id.usersProfileEdit:

                if (profile.getVisibility() == View.GONE) {
                    profile.setVisibility(View.VISIBLE);
                    saveUpdates.setVisibility(View.VISIBLE);
                } else if (phone.getVisibility() == View.VISIBLE && profile.getVisibility() == View.VISIBLE) {
                    profile.setVisibility(View.GONE);
                } else {
                    profile.setVisibility(View.GONE);
                    saveUpdates.setVisibility(View.GONE);
                }

                break;
        }
    }

    @Override
    public void onResume() {


        fundoo.setVisibility(View.GONE);
        pay.setVisibility(View.GONE);
        navigationIcon.setVisibility(View.VISIBLE);
        addAccount.setVisibility(View.VISIBLE);
        addAccount.setText("Settings");
        mainToolbar.setVisibility(View.VISIBLE);

        super.onResume();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {

            fundoo.setVisibility(View.GONE);
            pay.setVisibility(View.GONE);
            navigationIcon.setVisibility(View.VISIBLE);
            addAccount.setVisibility(View.VISIBLE);
            addAccount.setText("Settings");
            mainToolbar.setVisibility(View.VISIBLE);

        }
    }
}
