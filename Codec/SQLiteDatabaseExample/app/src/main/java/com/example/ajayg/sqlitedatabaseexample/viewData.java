package com.example.ajayg.sqlitedatabaseexample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.ajayg.sqlitedatabaseexample.R.id.storeData;

public class viewData extends AppCompatActivity {

    DatabaseHelper myData;
    ArrayList<DataModel> dataModels;
    RecyclerView recycler;
    ViewHolder viewAdapter;
    DatabaseReference reference;

    static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        reference = FirebaseDatabase.getInstance().getReference().child("Data");

        myData = new DatabaseHelper(this);

        dataModels = new ArrayList<>();

        recycler = (RecyclerView) findViewById(R.id.userRecycler);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = myData.getAllData();

        if(cursor.getCount() == 0){
            reference.removeValue();
            Toast.makeText(this, "No data!!", Toast.LENGTH_SHORT).show();
        }else{
            reference.removeValue();

            if(cursor.moveToFirst()){
                do {

                    id = cursor.getString(0);

                    DataModel data = new DataModel(cursor.getString(0),cursor.getString(1), cursor.getString(2));
                    storeDataAtFirebase(cursor);
                    dataModels.add(data);

                }while (cursor.moveToNext());

                viewAdapter = new ViewHolder(dataModels);
                recycler.setAdapter(viewAdapter);
            }
        }
    }

    private void storeDataAtFirebase(Cursor cursor) {

        if(isNetworkAvailable()) {
            reference.child(cursor.getString(0)).child("id").setValue(cursor.getString(0));
            reference.child(cursor.getString(0)).child("title").setValue(cursor.getString(1));
            reference.child(cursor.getString(0)).child("desc").setValue(cursor.getString(2));
        }else{
            Toast.makeText(this, "No internet Connection!!!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
