package com.bridgelabz.samplesqlitedatabase;

import android.accessibilityservice.AccessibilityService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.InetAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText title,desc;
    Button submit;

    BroadcastReceiver receiver;

    CollectionReference collectionReference;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        collectionReference = FirebaseFirestore.getInstance().collection("Data");

        context = getApplicationContext();

        title = (EditText) findViewById(R.id.editTitle);
        desc = (EditText) findViewById(R.id.editDesc);
        submit = (Button) findViewById(R.id.buttonSubmit);

        receiver = new Broadcastreceiver();
        getApplicationContext().registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_title = title.getText().toString();
                String user_desc = desc.getText().toString();

                if (isNetworkConnected()){

                    if (isInternetAvailable()){

                        Map<String, Object> userData = new HashMap<>();

                        userData.put("Title", user_title);
                        userData.put("Desc", user_desc);

                        collectionReference.document().set(userData);

                    }

                }
            }
        });
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("");
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }

    }
}
