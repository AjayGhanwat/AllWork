package com.bridgelabz.googlekeepdemo.register.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bridgelabz.googlekeepdemo.MainActivity;
import com.bridgelabz.googlekeepdemo.R;
import com.bridgelabz.googlekeepdemo.base.BaseActivity;
import com.bridgelabz.googlekeepdemo.register.presenter.RegisterUserData;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, RegisterData {

    Toolbar toolbar;

    String mFirst, mLast, mEmail, mPhone, mPass, mComparePass;

    RegisterUserData presenter;
    boolean isValide = true;
    ProgressDialog progress;
    private Button btn_Register;
    private EditText user_First_name, user_Last_Name, user_Email, user_Phone, user_First_Password, user_Second_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = (Toolbar) findViewById(R.id.maintoolbar);
        setSupportActionBar(toolbar);

        setTitle("Register");
        initView();
        clickListner();

        user_First_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {

                    mFirst = user_First_name.getText().toString();

                    if (mFirst.isEmpty() || mFirst.length() > 20) {
                        user_First_name.setError("Enter Correct User Name");
                    } else if (!mFirst.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) {
                        user_First_name.setError("Enter Correct User Name");
                    }

                }
            }
        });

        user_Last_Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {

                    mLast = user_Last_Name.getText().toString();

                    if (mLast.isEmpty() || mLast.length() > 20) {

                        user_Last_Name.setError("Enter Correct Last Name");
                    } else if (!mLast.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) {

                        user_Last_Name.setError("Enter Correct Last Name");
                    }
                }
            }
        });

        user_Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    mEmail = user_Email.getText().toString();

                    if (mEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
                        user_Email.setError("Enter Correct Email");
                    }
                }
            }
        });

        user_Phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    mPhone = user_Phone.getText().toString();

                    if (mPhone.isEmpty() || mPhone.length() != 10) {

                        user_Phone.setError("Enter Correct Phone Number");

                    } else if (!mPhone.matches("^[0-9]{1}[0-9]{9}$")) {
                        user_Phone.setError("Enter Correct Phone Number");
                    }
                }
            }
        });
        user_First_Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    mPass = user_First_Password.getText().toString();
                    if (mPass.isEmpty() || mPass.length() < 6) {
                        user_First_Password.setError("Minimum 6 Charachter");
                    } else if (!mPass.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}[0-9]{0,}$")) {
                        user_First_Password.setError("Minimum 6 Charachter");
                    }
                }
            }
        });
        user_Second_Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    mComparePass = user_Second_Password.getText().toString();
                    if (mComparePass.isEmpty() || !mComparePass.equals(mPass)) {
                        user_Second_Password.setError("Password Not Matched!!");
                    }
                }
            }
        });

    }

    @Override
    public void initView() {

        presenter = new RegisterUserData(this, this);

        user_First_name = (EditText) findViewById(R.id.signFName);
        user_Last_Name = (EditText) findViewById(R.id.signLName);
        user_Email = (EditText) findViewById(R.id.signEmail);
        user_Phone = (EditText) findViewById(R.id.signPhone);
        user_First_Password = (EditText) findViewById(R.id.signPassword);
        user_Second_Password = (EditText) findViewById(R.id.signCPassword);
        btn_Register = (Button) findViewById(R.id.signRegister);

    }

    @Override
    public void clickListner() {
        btn_Register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.signRegister:

                if (isValid()) {
                    presenter.getData(mFirst, mLast, mEmail, mPhone, mPass);
                }
                break;
        }
    }

    // It gets the user Entered Info from edit text

    void getEnteredData() {

        mFirst = user_First_name.getText().toString();
        mLast = user_Last_Name.getText().toString();
        mEmail = user_Email.getText().toString();
        mPhone = user_Phone.getText().toString();
        mPass = user_First_Password.getText().toString();
        mComparePass = user_Second_Password.getText().toString();

    }

    /*
     *  Check the user Entered Data is Valid or not.
     *  if valid then the next activity performed
     *  if not valid then it throws the error
     */

    private boolean isValid() {

        getEnteredData();

        if (mFirst.isEmpty() || mFirst.length() > 20) {
            user_First_name.setError("Enter Correct User Name");
            isValide = false;
        } else if (!mFirst.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) {
            user_First_name.setError("Enter Correct User Name");
            isValide = false;
        }


        if (mLast.isEmpty() || mLast.length() > 20) {
            user_Last_Name.setError("Enter Correct Last Name");
            isValide = false;
        } else if (!mLast.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) {

            user_Last_Name.setError("Enter Correct Last Name");
            isValide = false;
        }

        if (mEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
            user_Email.setError("Enter Correct Email");
            isValide = false;
        }


        if (mPhone.isEmpty() || mPhone.length() != 10) {

            user_Phone.setError( "Enter Correct Phone Number");
            isValide = false;

        } else if (!mPhone.matches("^[0-9]{1}[0-9]{9}$")) {
            user_Phone.setError( "Enter Correct Phone Number");
            isValide = false;
        }
        if (mPass.isEmpty() || mPass.length() < 6) {
            user_First_Password.setError("Minimum 6 Charachter");
            isValide = false;
        } else if (!mPass.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}[0-9]{0,}$")) {
            user_First_Password.setError("Minimum 6 Charachter");
            isValide = false;
        }

        if (mComparePass.isEmpty() || !mComparePass.equals(mPass)) {
            user_Second_Password.setError("Password Not Matched!!");
            isValide = false;
        }

        return isValide;
    }

    @Override
    public void registerSuccesful(String message) {
        Intent i = new Intent(this, MainActivity.class);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    @Override
    public void registerUnsuccessful(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(String message) {
        progress = new ProgressDialog(this);
        progress.setMessage(message);
        progress.show();
    }

    @Override
    public void dismissProgress() {
        progress.dismiss();
    }
}
