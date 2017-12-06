package com.example.ajayg.sqlitedatabaseexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    AppCompatEditText titletext, descText;
    AppCompatButton storedata, view;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titletext = (AppCompatEditText) findViewById(R.id.titletext);
        descText = (AppCompatEditText) findViewById(R.id.desctext);
        storedata = (AppCompatButton) findViewById(R.id.storeData);
        view = (AppCompatButton) findViewById(R.id.viewData);

        databaseHelper = new DatabaseHelper(this);

        storedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String key = viewData.id;

                if(key != null) {

                    id = Integer.parseInt(key);

                    id++;
                }else{
                    id = 1;
                }


                boolean isAdded = databaseHelper.insertData(new DataModel(String.valueOf(id), titletext.getText().toString(), descText.getText().toString()));

                if(isAdded)
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "UnSuccess", Toast.LENGTH_SHORT).show();

                titletext.setText("");
                descText.setText("");
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, viewData.class);
                startActivity(i);

            }
        });
    }
}
