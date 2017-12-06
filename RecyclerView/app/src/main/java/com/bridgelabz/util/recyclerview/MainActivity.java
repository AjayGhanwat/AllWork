package com.bridgelabz.util.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout relativeLayout = new RelativeLayout(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        relativeLayout.addView(recyclerView);

        ArrayList<studentSubjects> subjects = new ArrayList();

        studentSubjects math = new studentSubjects("Math", false, R.drawable.maths);
        subjects.add(math);

        studentSubjects science = new studentSubjects("Science", true, R.drawable.science);
        subjects.add(science);

        studentSubjects reading = new studentSubjects("Reading", false, R.drawable.reading);
        subjects.add(reading);

        studentSubjects history = new studentSubjects("History", false, R.drawable.history);
        subjects.add(history);

        studentSubjects biology = new studentSubjects("Biology", false, R.drawable.biology);
        subjects.add(biology);

        StudentAdapter studentAdapter = new StudentAdapter(this, subjects);
        recyclerView.setAdapter(studentAdapter);
    }
}