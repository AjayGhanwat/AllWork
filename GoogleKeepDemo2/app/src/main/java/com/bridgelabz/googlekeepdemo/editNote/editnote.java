package com.bridgelabz.googlekeepdemo.editNote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.bridgelabz.googlekeepdemo.MainActivity;
import com.bridgelabz.googlekeepdemo.R;
import com.bridgelabz.googlekeepdemo.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class editnote extends BaseActivity {

    Toolbar toolbar;

    EditText editTitle,editNotes;
    TextView editedDate;

    String user_title, user_desc, user_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        toolbar = (Toolbar) findViewById(R.id.maintoolbar);

        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorDefault));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("");

        Bundle extras = getIntent().getExtras();
        user_title = extras.getString("Title");
        user_desc = extras.getString("Desc");
        user_key = extras.getString("Key");

        initView();
        clickListner();
        getData();
    }

    private void getData() {

        Date date = new Date();
        String currentTime = new SimpleDateFormat("hh:mm aaa").format(date);

        editedDate.setText("Edited " + currentTime);

    }

    @Override
    public void initView() {
        editTitle = (EditText) findViewById(R.id.editTitle);
        editNotes = (EditText) findViewById(R.id.editNotes);

        editTitle.setText(user_title);
        editNotes.setText(user_desc);

        editedDate = (TextView) findViewById(R.id.editedDate);
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return super.onSupportNavigateUp();
    }
}
