package com.bridgelabz.fundoopay.register;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bridgelabz.fundoopay.R;
import com.bridgelabz.fundoopay.base.BaseFragment;
import com.bridgelabz.fundoopay.register.verifyComplete;

import java.util.List;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;

public class verifyAccount extends BaseFragment implements View.OnClickListener {

    Fragment mFragmentAccountVerify = null;
    TextView nextVerifyComplete;
    Button simFirst, simSec;
    View v;
    String simTwoNumber = null, simOneNumber = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        return inflater.inflate(R.layout.activity_verify_account, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v = view;
        initView();
        clickListner();
        initToolbarView();

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
    public void initView() {

        nextVerifyComplete = (TextView) v.findViewById(R.id.nextVerifyComplete);

        simFirst = (Button) v.findViewById(R.id.sim1Selection);
        simSec = (Button) v.findViewById(R.id.sim2Selection);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

            getsimDetails();

            simFirst.setText(simOneNumber);
            simSec.setText(simTwoNumber);

        } else {
            isPermissionGrantedPhone();
        }

    }

    @Override
    public void initToolbarView() {

    }

    @Override
    public void clickListner() {

        nextVerifyComplete.setOnClickListener(this);
        simFirst.setOnClickListener(this);
        simSec.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.sim1Selection:

                simFirst.setBackground(getResources().getDrawable(R.drawable.sim1_select_first));
                simSec.setBackground(getResources().getDrawable(R.drawable.sim2_select_first));

                break;

            case R.id.sim2Selection:

                simFirst.setBackground(getResources().getDrawable(R.drawable.sim1_select_sec));
                simSec.setBackground(getResources().getDrawable(R.drawable.sim2_select_sec));

                break;

            case R.id.nextVerifyComplete:

                mFragmentAccountVerify = new verifyComplete();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainactivityPanel, mFragmentAccountVerify).addToBackStack(null);
                fragmentTransaction.commit();

                break;

        }

    }

    public void getsimDetails() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                SubscriptionManager subManager = (SubscriptionManager) getActivity().getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
                List<SubscriptionInfo> subInfoList = null;
                subInfoList = subManager.getActiveSubscriptionInfoList();
                if (subInfoList != null && subInfoList.size() > 0) {
                    switch (subInfoList.size()) {
                        case 2:
                            simTwoNumber = (String) subInfoList.get(1).getDisplayName();
                            Log.i("Number", "getsimDetails: " + subInfoList.get(1).getNumber());
                        case 1:
                            simOneNumber = (String) subInfoList.get(0).getDisplayName();
                            Log.i("Number", "getsimDetails: " + subInfoList.get(0).getNumber());
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 2: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    initView();
                }
            }
        }
    }
}
