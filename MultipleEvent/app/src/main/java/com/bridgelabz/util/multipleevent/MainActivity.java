package com.bridgelabz.util.multipleevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button clickButton = (Button) findViewById(R.id.btnClick);

        clickButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        TextView txtViewText = (TextView) findViewById(R.id.textView);
                        txtViewText.setText("Hie Hi Broo!!!!");
                    }
                }
        );

        clickButton.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        TextView txtViewText = (TextView) findViewById(R.id.textView);
                        txtViewText.setText("How Are You!!!!");
                        return true;
                    }
                }
        );
    }
}
