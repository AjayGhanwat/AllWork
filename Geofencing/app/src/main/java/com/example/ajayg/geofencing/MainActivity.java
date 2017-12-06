package com.example.ajayg.geofencing;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ajayg.geofencing.adapter.AllDataAdapter;
import com.example.ajayg.geofencing.adapter.EnterExitAdapter;
import com.example.ajayg.geofencing.database.SQLiteDatabaseHandler;
import com.example.ajayg.geofencing.datamodel.AllData;
import com.example.ajayg.geofencing.datamodel.EnterExit;
import com.example.ajayg.geofencing.service.addGeofencing;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<EnterExit> list;
    static EnterExitAdapter enterExitAdapter;
    static RecyclerView enterExitListView;
    public static boolean activity;
    static SQLiteDatabaseHandler sqLiteDatabaseHandler;

    static AllDataAdapter allDataAdapter;
    static ArrayList<AllData> allData;
    static RecyclerView allDataRecyclerview;

    public static void changeData() {

        list.clear();

        list = getAll();

        enterExitAdapter = new EnterExitAdapter(list);

        enterExitListView.setAdapter(enterExitAdapter);

        enterExitAdapter.notifyDataSetChanged();

    }

    private static ArrayList<EnterExit> getAll() {
        list = sqLiteDatabaseHandler.getAllRecordEnterExit();
        return list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = false;

        list = new ArrayList<>();

        allData = new ArrayList<>();

        sqLiteDatabaseHandler = new SQLiteDatabaseHandler(this);

        Intent intent = new Intent(this, addGeofencing.class);
        startService(intent);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 5);
            }
        }

        enterExitListView = (RecyclerView) findViewById(R.id.enterExitTime);
        enterExitListView.setLayoutManager(new LinearLayoutManager(this));
        enterExitListView.setHasFixedSize(true);

        allDataRecyclerview = (RecyclerView) findViewById(R.id.AllDateData);
        allDataRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        allDataRecyclerview.setHasFixedSize(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        activity = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (activity) {
            changeData();
            changeAllData();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        activity = false;
    }

    public static void changeAllData() {

        allData.clear();

        allData = getAllData();

        allDataAdapter = new AllDataAdapter(allData);

        allDataRecyclerview.setAdapter(allDataAdapter);

        allDataAdapter.notifyDataSetChanged();

    }

    private static ArrayList<AllData> getAllData() {
        allData = sqLiteDatabaseHandler.getAllRecordAllData();
        return allData;
    }
}