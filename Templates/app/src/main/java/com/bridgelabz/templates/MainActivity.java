package com.bridgelabz.templates;

import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ActionBarDrawerToggle abdt;
    DrawerLayout mDrawerLayout;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        abdt = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.Open, R.string.Close);

        abdt.setDrawerIndicatorEnabled(true);

        mDrawerLayout.addDrawerListener(abdt);

        abdt.syncState();

        NavigationView nav = (NavigationView) findViewById(R.id.MainNav);

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                displaySelectedItem(id);

                return false;
            }
        });

        displaySelectedItem(R.id.Notes);
    }

    public void displaySelectedItem(int id){
        if(id == R.id.Notes){
            fragment = new Notes();
            Toast.makeText(this, "Notes", Toast.LENGTH_SHORT).show();
            setTitle("Notes");
        }
        else if(id == R.id.Reminder){
            fragment = new Reminder();
            Toast.makeText(this, "Reminder", Toast.LENGTH_SHORT).show();
            setTitle("Reminder");
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
