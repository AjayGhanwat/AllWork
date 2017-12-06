package com.bridgelabz.textinput;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText email,pass1,pass2;
    Button register;
    TextView already;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.userEmail);
        pass1 = (EditText) findViewById(R.id.userPassord);
        pass2 = (EditText) findViewById(R.id.userConfirmPass);
        register = (Button) findViewById(R.id.userRegister);
        already = (TextView) findViewById(R.id.userAlready);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cemiil = email.getText().toString();
                String pass = pass1.getText().toString();
                String cpass = pass2.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("register", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();

                if(!cemiil.equals("")) {
                    if (pass.equals(cpass)) {
                        Intent i = new Intent(MainActivity.this, Login.class);
                        editor.putString("userEmail", cemiil);
                        editor.putString("userPass", pass);
                        editor.apply();
                        Toast.makeText(getApplicationContext(), "Success!!", Toast.LENGTH_SHORT).show();
                        startActivity(i);

                    } else {
                        Toast.makeText(getApplicationContext(), "Enter Correct Password!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
            }
        });
    }
}
