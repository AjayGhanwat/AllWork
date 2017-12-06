package com.bridgelabz.googlekeepdemo.addNote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bridgelabz.googlekeepdemo.MainActivity;
import com.bridgelabz.googlekeepdemo.R;
import com.bridgelabz.googlekeepdemo.base.BaseActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddNote extends BaseActivity {

    Toolbar toolbar;

    EditText addTitle,addNotes;
    TextView todayDate;

    CollectionReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        toolbar = (Toolbar) findViewById(R.id.maintoolbar);

        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorDefault));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("");

        initView();
        clickListner();
        getData();
    }

    private void getData() {

        Date date = new Date();
        String currentTime = new SimpleDateFormat("hh:mm aaa").format(date);

        todayDate.setText("Edited " + currentTime);

    }

    @Override
    public void initView() {
        addTitle = (EditText) findViewById(R.id.addTitle);
        addNotes = (EditText) findViewById(R.id.addNotes);

        todayDate = (TextView) findViewById(R.id.todayDate);
    }

    @Override
    public void clickListner() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addnote, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.pinNote :
                Toast.makeText(this, "pinNote", Toast.LENGTH_SHORT).show();
                break;

            case R.id.alarmNote :
                Toast.makeText(this, "alarmNote", Toast.LENGTH_SHORT).show();
                break;

            case R.id.colorNote :
                Toast.makeText(this, "colorNote", Toast.LENGTH_SHORT).show();
                break;

            case R.id.saveNote :
                getDataStored();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void getDataStored() {

        int a =2;

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mRef = FirebaseFirestore.getInstance().collection("Notes").document(userID).collection("Note");

        String title = addTitle.getText().toString();
        String desc = addNotes.getText().toString();
        String id = String.valueOf(a);

        Map<String, Object> note = new HashMap<>();

        note.put("Title", title);
        note.put("Note", desc);
        note.put("id", id);
        note.put("location", "note");
        a++;

        mRef.document().set(note);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return super.onSupportNavigateUp();
    }
}
