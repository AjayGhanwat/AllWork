package com.bridgelabz.fundoopay.register;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bridgelabz.fundoopay.R;
import com.bridgelabz.fundoopay.base.BaseFragment;
import com.bridgelabz.fundoopay.register.verifyAccount;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;

public class allowAccess extends BaseFragment implements View.OnClickListener {


    Fragment mFragmentAccess = null;
    TextView nextVerify;
    Switch smsAllow, phoneState;
    View v;
    boolean isGrantedSMS;
    boolean isGrantedPhone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        return inflater.inflate(R.layout.activity_allow_access, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v = view;
        initView();
        clickListner();
        initToolbarView();

    }

    @Override
    public void initView() {
        nextVerify = (TextView) v.findViewById(R.id.nextSelectSim);

        smsAllow = (Switch) v.findViewById(R.id.smsAccess);
        phoneState = (Switch) v.findViewById(R.id.phoneState);
    }

    @Override
    public void initToolbarView() {

    }

    @Override
    public void clickListner() {
        nextVerify.setOnClickListener(this);
        smsAllow.setOnClickListener(this);
        phoneState.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.smsAccess:

                if (smsAllow.isChecked()) {

                    isGrantedSMS = isPermissionGrantedSMS();

                }

                break;

            case R.id.phoneState:

                if (phoneState.isChecked()) {

                    isGrantedPhone = isPermissionGrantedPhone();

                }

                break;

            case R.id.nextSelectSim:

                if (isGrantedPhone && phoneState.isChecked() || isGrantedSMS && smsAllow.isChecked()) {

                    mFragmentAccess = new verifyAccount();

                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.mainactivityPanel, mFragmentAccess).addToBackStack(null);
                    fragmentTransaction.commit();
                } else {

                    Toast.makeText(getActivity(), "Allow Permission!!", Toast.LENGTH_SHORT).show();

                }

                break;
        }
    }

    public boolean isPermissionGrantedSMS() {
        if (checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
            }
            return false;
        }
    }

    public boolean isPermissionGrantedPhone() {
        if (checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 2);
            }
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    isGrantedSMS = true;

                } else {

                    smsAllow.setChecked(false);
                    isGrantedSMS = false;
                }
            }

            case 2: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    isGrantedPhone = true;

                } else {

                    phoneState.setChecked(false);
                    isGrantedPhone = false;
                }
            }

        }
    }
}
