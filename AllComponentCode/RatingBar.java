package com.bridgelabz.util.myapplication;

import android.content.DialogInterface;
import android.graphics.Color;
import android.media.Rating;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.nio.channels.AlreadyBoundException;

import static android.R.attr.country;
import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {

    RatingBar rate;
    Button cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButtonClick();

    }

    private void addListenerOnButtonClick() {

        cal = (Button) findViewById(R.id.button3);
        rate = (RatingBar) findViewById(R.id.ratingBar);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ratting = String.valueOf(rate.getRating());
                Toast.makeText(MainActivity.this, ratting, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
