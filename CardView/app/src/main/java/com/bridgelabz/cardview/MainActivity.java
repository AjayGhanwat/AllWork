package com.bridgelabz.cardview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recycler;
    ArrayList<Data> data;
    userViewAdapter dataAdapter;
    LinearLayoutManager linearLayoutManager;
    StaggeredGridLayoutManager gridLayoutManager;

    RecyclerView.LayoutManager layoutManager;

    static String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reference = FirebaseDatabase.getInstance().getReference().child("Data");

        recycler = (RecyclerView) findViewById(R.id.recyclerViewNote);

        linearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new StaggeredGridLayoutManager(2, 1);

        layoutManager = linearLayoutManager;

        recycler.setLayoutManager(layoutManager);

        data = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        updateResult();
    }

    @Override
    protected void onResume() {
        super.onResume();
        data.clear();
    }

    void updateResult() {
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                GenericTypeIndicator<ArrayList<Data>> t = new GenericTypeIndicator<ArrayList<Data>>() {
                };

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Data match = postSnapshot.getValue(Data.class);
                    data.add(match);
                    Log.i("sd", "onChildAdded: "+match);
                    dataAdapter = new userViewAdapter(data);
                    recycler.setAdapter(dataAdapter);
                    dataAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.addNote) {

            Intent i = new Intent(this, AddNote.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);

        } else if (item.getItemId() == R.id.layoutManager) {

            if (!linearLayoutManager.equals(layoutManager)) {
                layoutManager = linearLayoutManager;
                recycler.setLayoutManager(layoutManager);
            } else if (linearLayoutManager.equals(layoutManager)) {
                layoutManager = gridLayoutManager;
                recycler.setLayoutManager(layoutManager);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}