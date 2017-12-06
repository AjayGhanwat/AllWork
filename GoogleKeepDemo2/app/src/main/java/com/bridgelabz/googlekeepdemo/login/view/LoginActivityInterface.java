package com.bridgelabz.googlekeepdemo.login.view;

public interface LoginActivityInterface {

    void loginUserSucces(String msg);
    void loginUserUnsucces(String msg);
    void progressShow(String msg);
    void progressDismiss();
}
