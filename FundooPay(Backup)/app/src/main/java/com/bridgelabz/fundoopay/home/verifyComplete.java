package com.bridgelabz.fundoopay.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bridgelabz.fundoopay.MainActivity;
import com.bridgelabz.fundoopay.R;
import com.bridgelabz.fundoopay.base.BaseFragment;

public class verifyComplete extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.verification, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new selectBankName();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.verificationComplete, fragment);
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public void initView() {

    }

    @Override
    public void clickListner() {

    }

    @Override
    public void onStop() {
        super.onStop();
        //getFragmentManager().popBackStackImmediate(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
