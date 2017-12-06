package com.bridgelabz.util.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText studName,studGrade;
    Button addStud,retriveStud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studName = (EditText) findViewById(R.id.studName);
        studGrade = (EditText) findViewById(R.id.studGrade);
        addStud = (Button) findViewById(R.id.addStudent);
        retriveStud =(Button) findViewById(R.id.retriveStudent);

        addStud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values = new ContentValues();

                values.put(StudentProvider.NAME, studName.getText().toString());
                values.put(StudentProvider.GRADE, studGrade.getText().toString());

                Uri uri = getContentResolver().insert(StudentProvider.CONTENT_URI, values);

                Toast.makeText(getApplicationContext(), uri.toString(), Toast.LENGTH_LONG).show();
            }
        });

        retriveStud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String URL = "content://com.example.provider.College/students";

                Uri students = Uri.parse(URL);

                Cursor c = managedQuery(students, null, null, null, "name");
                if (c.moveToFirst()) {
                    do{
                        Toast.makeText(MainActivity.this,
                                c.getString(c.getColumnIndex(StudentProvider._ID)) +
                                        ", " + c.getString(c.getColumnIndex( StudentProvider.NAME))
                                        +
                                        ", " + c.getString(c.getColumnIndex(
                                        StudentProvider.GRADE)),
                                Toast.LENGTH_SHORT).show();
                    } while (c.moveToNext());
                }

            }
        });

    }
}
