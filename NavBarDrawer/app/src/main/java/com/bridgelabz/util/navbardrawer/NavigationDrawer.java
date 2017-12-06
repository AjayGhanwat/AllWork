package com.bridgelabz.util.navbardrawer;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import static com.bridgelabz.util.navbardrawer.R.id.nav_view;

public class NavigationDrawer extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        abdt = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);

        abdt.setDrawerIndicatorEnabled(true);

        drawerLayout.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.myprofile){
                    Toast.makeText(NavigationDrawer.this,"MyProfile", Toast.LENGTH_LONG).show();
                }
                else if(id == R.id.setting){
                    Toast.makeText(NavigationDrawer.this,"Setting", Toast.LENGTH_LONG).show();
                }
                else if(id == R.id.editprofile){
                    Toast.makeText(NavigationDrawer.this,"Edit Profile", Toast.LENGTH_LONG).show();
                }


                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
