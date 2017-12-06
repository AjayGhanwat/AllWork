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

import org.w3c.dom.Text;

public class Main extends AppCompatActivity {

    TextView useremail, userpass;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        useremail = (TextView) findViewById(R.id.mainEmail);
        userpass = (TextView) findViewById(R.id.mainPass);
        logout = (Button) findViewById(R.id.userLogout);

        SharedPreferences sharedPreferences = getSharedPreferences("register", Context.MODE_PRIVATE);

        final SharedPreferences.Editor editor = sharedPreferences.edit();

        String cemil = sharedPreferences.getString("userEmail", null);
        String cpass = sharedPreferences.getString("userPass", null);

        useremail.setText("User Name = " + cemil);
        userpass.setText("Passsword = "+ cpass);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main.this, MainActivity.class);
                editor.clear();
                Toast.makeText(getApplicationContext(), "Data Cleared!!", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
    }
}
