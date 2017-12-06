package com.bridgelabz.util.intentview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText name,age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = (Button) findViewById(R.id.submit);
        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, display.class);
                i.putExtra("name", name.getText().toString());
                i.putExtra("age", age.getText().toString());
                startActivity(i);
            }
        });
    }
}
