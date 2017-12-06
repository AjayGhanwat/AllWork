package com.bridgelabz.cardview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kizitonwose.colorpreference.ColorDialog;
import com.kizitonwose.colorpreference.ColorPreference;
import com.kizitonwose.colorpreference.ColorShape;

import java.text.SimpleDateFormat;
import java.util.Date;


public class AddNote extends AppCompatActivity implements ColorDialog.OnColorSelectedListener {

    DatabaseReference reference;

    EditText mTitle, mDesc;

    String title, decs;

    int userColor = 16777215;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        mTitle = (EditText) findViewById(R.id.TitleTExt);
        mDesc = (EditText) findViewById(R.id.DescTExt);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.addColor) {

            new ColorDialog.Builder(this)
                    .setColorShape(ColorShape.CIRCLE)
                    .setSelectedColor(Color.WHITE)
                    .show();

        } else if (item.getItemId() == R.id.saveNote) {

            String key1;

            String previousDate = userViewAdapter.date;

            Date cDate = new Date();
            String fDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);

            if(previousDate != null ) {
                if (previousDate.equals(fDate)) {
                    if (userViewAdapter.key != null) {
                        String id = userViewAdapter.key;

                        int key = Integer.parseInt(id);
                        key++;

                        key1 = key + "";
                    } else {
                        key1 = "0";
                    }
                } else{

                    if (userViewAdapter.key != null) {
                        int key = -1;
                        key++;

                        key1 = key + "";

                        previousDate = fDate;

                    } else {
                        key1 = "0";
                    }
                }
            }else{
                previousDate = fDate;
                key1 = "0";
            }

            title = mTitle.getText().toString();
            decs = mDesc.getText().toString();

            reference = FirebaseDatabase.getInstance().getReference().child("Data").child(fDate).child(key1);

            reference.child("Title").setValue(title);
            reference.child("Desc").setValue(decs);
            reference.child("Key").setValue(key1);
            reference.child("Color").setValue(userColor);
            reference.child("Date").setValue(fDate);

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onColorSelected(int i, String s) {

        userColor = i;
        getWindow().getDecorView().setBackgroundColor(userColor);

    }
}
