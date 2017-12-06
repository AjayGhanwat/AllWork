package com.bridgelabz.util.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DatePicker date;
    TextView tvdate;
    Button btnDate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = (DatePicker) findViewById(R.id.datePicker);
        tvdate = (TextView) findViewById(R.id.textView4);
        btnDate = (Button) findViewById(R.id.btnDate);

        tvdate.setText(getCurrentDate());

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvdate.setText(getCurrentDate());
            }
        });
    }

    public String getCurrentDate() {
        StringBuilder dateis = new StringBuilder();
        dateis.append((date.getDayOfMonth())+"/");
        dateis.append((date.getMonth()+1)+"/");
        dateis.append(date.getYear());
        return dateis.toString();
    }
}
