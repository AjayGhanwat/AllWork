package com.bridgelabz.readsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity{

    public static final String OTP_REGEX = "[0-9]{1,6}";

    String otp = null;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.otp);

        SmsReceived.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {

                Pattern pattern = Pattern.compile(OTP_REGEX);
                Matcher matcher = pattern.matcher(messageText);
                while (matcher.find())
                {
                    otp = matcher.group();
                }

                textView.setText(otp);

            }
        });
    }
}