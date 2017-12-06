package com.bridgelabz.fundoopay;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.bridgelabz.fundoopay.base.BaseActivity;
import com.bridgelabz.fundoopay.register.welcomefragment;

public class MainActivity extends BaseActivity {

    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new welcomefragment();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainactivityPanel, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void initView() {

    }

    @Override
    public void clickListner() {

    }
}
