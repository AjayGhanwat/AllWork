package com.bridgelabz.fundoopay.bankData.view;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bridgelabz.fundoopay.R;
import com.bridgelabz.fundoopay.base.BaseFragment;
import com.bridgelabz.fundoopay.home.view.userBankAccount;

public class resetUpiNewPin extends BaseFragment implements View.OnClickListener, TextWatcher, View.OnKeyListener {


    EditText firstNum, secNum, thNum, fourNum;
    View v;
    Fragment fragment;
    Button set;

    Toolbar mainToolbar;
    TextView fundoo, pay, addAccount;
    ImageButton navigationIcon;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        return inflater.inflate(R.layout.activity_set_up_pin, container, false);
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

        firstNum = (EditText) v.findViewById(R.id.firstNum);
        secNum = (EditText) v.findViewById(R.id.secNum);
        thNum = (EditText) v.findViewById(R.id.thNum);
        fourNum = (EditText) v.findViewById(R.id.fourNum);

        set = (Button) v.findViewById(R.id.set);
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
        firstNum.addTextChangedListener(this);
        secNum.addTextChangedListener(this);
        thNum.addTextChangedListener(this);
        fourNum.addTextChangedListener(this);

        firstNum.setOnKeyListener(this);
        secNum.setOnKeyListener(this);
        thNum.setOnKeyListener(this);
        fourNum.setOnKeyListener(this);

        set.setOnClickListener(this);
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (firstNum.hasFocus()) {
            if (firstNum.getText().toString().length() == 1) {
                secNum.requestFocus();
            }
        } else if (secNum.hasFocus()) {
            if (secNum.getText().toString().length() == 1) {
                thNum.requestFocus();
            }
        } else if (thNum.hasFocus()) {
            if (thNum.getText().toString().length() == 1) {
                fourNum.requestFocus();
            }
        } else if (fourNum.hasFocus()) {
            if (fourNum.getText().toString().length() == 1) {
                InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(fourNum.getWindowToken(), 0);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.set:

                fragment = new userBankAccount();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainactivityPanel, fragment).addToBackStack(null);
                fragmentTransaction.commit();

                break;

        }

    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != KeyEvent.KEYCODE_DEL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            if (i == KeyEvent.KEYCODE_DEL) {

                if (secNum.hasFocus()) {
                    if (secNum.getText().toString().length() == 0) {
                        firstNum.requestFocus();
                        firstNum.setText("");
                    } else if (secNum.getText().toString().length() == 1) {
                        secNum.setText("");
                    }
                } else if (thNum.hasFocus()) {
                    if (thNum.getText().toString().length() == 0) {
                        secNum.requestFocus();
                        secNum.setText("");
                    } else if (thNum.getText().toString().length() == 1) {
                        thNum.setText("");
                    }
                } else if (fourNum.hasFocus()) {
                    if (fourNum.getText().toString().length() == 0) {
                        thNum.requestFocus();
                        thNum.setText("");
                    } else if (fourNum.getText().toString().length() == 1) {
                        fourNum.setText("");
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void onResume() {

        addAccount.setVisibility(View.VISIBLE);
        addAccount.setText("RESET UPI PIN");
        navigationIcon.setVisibility(View.VISIBLE);
        fundoo.setVisibility(View.GONE);
        pay.setVisibility(View.GONE);
        mainToolbar.setVisibility(View.VISIBLE);

        super.onResume();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            addAccount.setVisibility(View.VISIBLE);
            addAccount.setText("RESET UPI PIN");
            navigationIcon.setVisibility(View.VISIBLE);
            fundoo.setVisibility(View.GONE);
            pay.setVisibility(View.GONE);
            mainToolbar.setVisibility(View.VISIBLE);
        }
    }
}
