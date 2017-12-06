package com.bridgelabz.fundoopay;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bridgelabz.fundoopay.base.BaseActivity;
import com.bridgelabz.fundoopay.home.verifyComplete;
import com.bridgelabz.fundoopay.home.welcomefragment;

public class MainActivity extends BaseActivity {

    Fragment fragment = null;

    public static Toolbar mainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mainToolbar);

        mainToolbar.setVisibility(View.GONE);

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

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
            mainToolbar.setVisibility(View.GONE);
        }
    }
}
