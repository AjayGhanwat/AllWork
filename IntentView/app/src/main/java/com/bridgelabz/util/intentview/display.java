package com.bridgelabz.util.intentview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class display extends AppCompatActivity {

    TextView name,age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        name = (TextView) findViewById(R.id.givenName);
        age = (TextView) findViewById(R.id.givenAge);

        Bundle val = getIntent().getExtras();

        name.setText("Name : " + val.getString("name"));
        age.setText("Age : " + val.getString("age"));

        name.callOnClick();
    }
}
