package com.bridgelabz.googlekeepdemo;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.bridgelabz.googlekeepdemo.archive.view.ArchiveFragment;
import com.bridgelabz.googlekeepdemo.login.view.LoginActivity;
import com.bridgelabz.googlekeepdemo.note.view.NoteFragment;
import com.bridgelabz.googlekeepdemo.reminder.view.ReminderFragment;
import com.bridgelabz.googlekeepdemo.trash.view.TrashFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    NavigationView nav_view;

    ProgressDialog progress;

    Fragment mFragment = null;

    Window window;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        window = getWindow();

        progress = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerMainMenu);

        toolbar = (Toolbar) findViewById(R.id.maintoolbar);

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);

        toggle.setDrawerIndicatorEnabled(true);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorDefault));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nav_view = (NavigationView) findViewById(R.id.mainNavigationMenu);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                displaySelectedFragment(id);

                return false;
            }
        });

        displaySelectedFragment(R.id.userNotes);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void displaySelectedFragment(int id) {

        if (id == R.id.userNotes) {
            setTitle("Note");
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
            mFragment = new NoteFragment();
        }
        else if (id == R.id.userReminders) {
            setTitle("Reminder");
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorReminder));
            window.setStatusBarColor(getResources().getColor(R.color.colorReminder));
            mFragment = new ReminderFragment();
        }
        else if (id == R.id.userArchive) {
            setTitle("Archive");
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorArchive));
            window.setStatusBarColor(getResources().getColor(R.color.colorArchive));
            mFragment = new ArchiveFragment();
        }
        else if (id == R.id.userTrash) {
            setTitle("Trash");
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorTrash));
            window.setStatusBarColor(getResources().getColor(R.color.colorTrash));
            mFragment = new TrashFragment();
        } else if (id == R.id.userLogout) {
            progress.setMessage("Loging Out");
            progress.show();
            mAuth.signOut();
            //LoginManager.getInstance().logOut();

            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            progress.dismiss();
            startActivity(i);
        }

        if (mFragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainFragment, mFragment);
            fragmentTransaction.commit();
        }

        drawerLayout.closeDrawer(GravityCompat.START);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_panel_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(Gravity.START))
            drawerLayout.closeDrawer(Gravity.START);
        else {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
            super.onBackPressed();
        }
    }
}
