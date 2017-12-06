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
import com.bridgelabz.fundoopay.home.allowAccess;

public class registerfragment extends BaseFragment implements View.OnClickListener{

    Fragment mFragmentReg = null;
    TextView nextGiveAccess;
    View v;
    TextView mUserName, mFullName, mAddress, mLandmark, mPinCode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_register, container, false);
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

        nextGiveAccess = (TextView) v.findViewById(R.id.nextGiveAccess);

        mUserName = (TextView) v.findViewById(R.id.userName);
        mFullName = (TextView) v.findViewById(R.id.fullName);
        mAddress = (TextView) v.findViewById(R.id.address);
        mLandmark = (TextView) v.findViewById(R.id.landmark);
        mPinCode = (TextView) v.findViewById(R.id.pinCode);

    }

    @Override
    public void clickListner() {

        nextGiveAccess.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        mFragmentReg = new allowAccess();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.registerFragment, mFragmentReg).addToBackStack( "Register" );
        fragmentTransaction.commit();

    }
}
