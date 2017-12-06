package com.bridgelabz.fundoopay.register;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bridgelabz.fundoopay.R;
import com.bridgelabz.fundoopay.base.BaseFragment;
import com.bridgelabz.fundoopay.register.registerfragment;

public class welcomefragment extends BaseFragment implements View.OnClickListener {

    Fragment mFragmentWelcome = null;
    TextView nextReg;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        return inflater.inflate(R.layout.activity_welcome, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v = view;

        initView();
        clickListner();

    }

    @Override
    public void initView() {

        nextReg = (TextView) v.findViewById(R.id.nextRegister);

    }

    @Override
    public void initToolbarView() {

    }

    @Override
    public void clickListner() {

        nextReg.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        mFragmentWelcome = new registerfragment();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainactivityPanel, mFragmentWelcome).addToBackStack(null);
        fragmentTransaction.commit();

    }
}
