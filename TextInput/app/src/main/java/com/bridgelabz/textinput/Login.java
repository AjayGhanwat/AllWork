package com.bridgelabz.textinput;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static junit.runner.Version.id;

public class Login extends AppCompatActivity {

    EditText email,pass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.userLoginEmail);
        pass = (EditText) findViewById(R.id.userLoginPass);
        login = (Button) findViewById(R.id.userLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("register", Context.MODE_PRIVATE);

                String cemil = sharedPreferences.getString("userEmail", null);
                String cpass = sharedPreferences.getString("userPass", null);
                String iemail = email.getText().toString();
                String ipass = pass.getText().toString();

                if(!iemail.equals("")){
                    if(!ipass.equals("")) {
                        if (iemail.equals(cemil) && ipass.equals(cpass)) {
                            Intent i = new Intent(Login.this, Main.class);
                            Toast.makeText(getApplicationContext(), "Login Success!!", Toast.LENGTH_SHORT).show();
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "Enter Correct Name and Password!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}
