package com.bridgelabz.cropperexample;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.bridgelabz.cropper.view.MainUserPalet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageManager packageManager = getApplicationContext().getPackageManager();
        boolean supportsPIP = packageManager.hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE);
        if( supportsPIP ) {
            onActivityClicked();
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainActivity, MainUserPalet.newInstance()).commit();
    }

    public void onActivityClicked() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.enterPictureInPictureMode();
        }
    }
}
