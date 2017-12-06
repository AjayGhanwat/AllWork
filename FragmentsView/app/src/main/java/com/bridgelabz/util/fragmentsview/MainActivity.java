package com.bridgelabz.util.fragmentsview;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.R.attr.fragment;
import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Configuration configuration = getResources().getConfiguration();

        if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            FragmentsLandscap fragmentsLandscap = new FragmentsLandscap();

            fragmentTransaction.replace(android.R.id.content, fragmentsLandscap);
        }
        else{
            FragmentsPotrint fragmentsPotrint = new FragmentsPotrint();

            fragmentTransaction.replace(android.R.id.content, fragmentsPotrint);
        }

        fragmentTransaction.commit();

        //setContentView(R.layout.activity_main);
    }
}
