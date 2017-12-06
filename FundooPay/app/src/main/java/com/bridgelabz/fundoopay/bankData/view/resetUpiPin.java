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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bridgelabz.fundoopay.R;
import com.bridgelabz.fundoopay.base.BaseFragment;

public class resetUpiPin extends BaseFragment implements View.OnClickListener, TextWatcher, View.OnKeyListener {

    Fragment fragment = null;
    EditText firstNum, secNum, thNum, fourNum, fiveNum, sixNum;
    EditText valideDate, valideYear;
    TextView submit;
    View v;

    Toolbar mainToolbar;
    TextView fundoo, pay, addAccount;
    ImageButton navigationIcon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        return inflater.inflate(R.layout.activity_set_up_pin_card_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.v = view;

        initView();
        clickListner();
        initToolbarView();

        navigationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        fiveNum = (EditText) v.findViewById(R.id.fiveNum);
        sixNum = (EditText) v.findViewById(R.id.sixNum);

        valideDate = (EditText) v.findViewById(R.id.valideDate);
        valideYear = (EditText) v.findViewById(R.id.valideYear);

        submit = (TextView) v.findViewById(R.id.setNewPin);
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


        submit.setOnClickListener(this);

        firstNum.addTextChangedListener(this);
        secNum.addTextChangedListener(this);
        thNum.addTextChangedListener(this);
        fourNum.addTextChangedListener(this);
        fiveNum.addTextChangedListener(this);
        sixNum.addTextChangedListener(this);

        valideDate.addTextChangedListener(this);
        valideYear.addTextChangedListener(this);

        firstNum.setOnKeyListener(this);
        secNum.setOnKeyListener(this);
        thNum.setOnKeyListener(this);
        fourNum.setOnKeyListener(this);
        fiveNum.setOnKeyListener(this);
        sixNum.setOnKeyListener(this);

        valideDate.setOnKeyListener(this);
        valideYear.setOnKeyListener(this);

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
                fiveNum.requestFocus();
            }
        } else if (fiveNum.hasFocus()) {
            if (fiveNum.getText().toString().length() == 1) {
                sixNum.requestFocus();
            }
        } else if (sixNum.hasFocus()) {
            if (sixNum.getText().toString().length() == 1) {
                InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(sixNum.getWindowToken(), 0);
            }
        }

        if (valideDate.hasFocus()) {
            if (valideDate.getText().toString().length() == 2) {
                valideYear.requestFocus();
            }
        } else if (valideYear.hasFocus()) {
            if (valideYear.getText().toString().length() == 2) {
                InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(valideYear.getWindowToken(), 0);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

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
                } else if (fiveNum.hasFocus()) {
                    if (fiveNum.getText().toString().length() == 0) {
                        fourNum.requestFocus();
                        fourNum.setText("");
                    } else if (fiveNum.getText().toString().length() == 1) {
                        fiveNum.setText("");
                    }
                } else if (sixNum.hasFocus()) {
                    if (sixNum.getText().toString().length() == 0) {
                        fiveNum.requestFocus();
                        fiveNum.setText("");
                    } else if (sixNum.getText().toString().length() == 1) {
                        sixNum.setText("");
                    }
                }

                if (valideDate.hasFocus()) {
                    if (valideDate.getText().toString().length() == 0) {
                        valideYear.requestFocus();
                        valideYear.setText("");
                    } else if (valideDate.getText().toString().length() == 1) {
                        valideDate.setText("");
                    }
                } else if (valideYear.hasFocus()) {
                    if (valideYear.getText().toString().length() == 0) {
                        valideDate.requestFocus();
                        valideDate.setText("");
                    } else if (valideYear.getText().toString().length() == 1) {
                        valideYear.setText("");
                    }
                }
            }
        }

        return false;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.setNewPin:

                fragment = new resetUpiNewPin();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainactivityPanel, fragment).addToBackStack(null);
                fragmentTransaction.commit();

                break;

        }

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
