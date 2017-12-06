package com.bridgelabz.googlekeepdemo.login.interacter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.bridgelabz.googlekeepdemo.login.presenter.LoginUserPresenter;
import com.bridgelabz.googlekeepdemo.login.presenter.LoginUserPresenterInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.net.InetAddress;

public class LoginUserInteracter implements LoginUserInteracterInterface {

    Context context;
    LoginUserPresenterInterface presenter;

    FirebaseAuth mAuth;

    public LoginUserInteracter(Context context, LoginUserPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    /*
     *  In the normal logging in that the user is present then the logging the user otherwise it toast the failed
     */

    @Override
    public void userPresent(String email, String pass) {

        if (isNetworkConnected()) {

            if (isInternetAvailable()) {
                mAuth = FirebaseAuth.getInstance();

                presenter.showProgress("Loging In");

                mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            presenter.isLoginSuccces("Logging Successfull");
                            presenter.dismissProgress();
                        } else {
                            presenter.isLoginUnSucces("Logging Failed");
                            presenter.dismissProgress();
                        }
                    }
                });

            } else
                Toast.makeText(context, "No Internet Vailable", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();


    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("");
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }

    }
}
