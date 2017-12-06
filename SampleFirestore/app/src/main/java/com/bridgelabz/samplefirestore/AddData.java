package com.bridgelabz.samplefirestore;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddData extends AppCompatActivity {

    EditText addTitle, addDesc;
    Button StoreData, featchData;
    RecyclerView recyclerView;

    ArrayList<userData> datas, user;
    ViewAdapter viewAdapter, adapter;

    Date date = new Date();

    String fDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

    private CollectionReference mRef = FirebaseFirestore.getInstance().collection("UserData").document("wmvpfoUxRvF2FiuF9flY").collection("Notes");
    //private CollectionReference docRef = FirebaseFirestore.getInstance().collection("UserData").document("wmvpfoUxRvF2FiuF9flY").collection("Date");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        datas = new ArrayList<>();
        user = new ArrayList<>();

        addTitle = (EditText) findViewById(R.id.addTitle);
        addDesc = (EditText) findViewById(R.id.addDesc);
        StoreData = (Button) findViewById(R.id.StoreData);
        featchData = (Button) findViewById(R.id.FeatchData);

        recyclerView = (RecyclerView) findViewById(R.id.notes);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        StoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String title = addTitle.getText().toString();
                final String desc = addDesc.getText().toString();
                final String key = mRef.document().getId();

                Map<String, Object> user = new HashMap<String, Object>();

                user.put("Title", title);
                user.put("Desc", desc);
                user.put("Key", key);
                user.put("Date", fDate);

                mRef.document(key).set(user);

                addTitle.setText("");
                addDesc.setText("");
            }
        });

        featchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                user.clear();

                mRef.whereEqualTo("Date", "2017-10-07").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {

                        for (DocumentSnapshot document : documentSnapshots.getDocuments()) {

                            userData data = document.toObject(userData.class);

                            user.add(data);
                            adapter = new ViewAdapter(user);
                            recyclerView.setAdapter(adapter);
                            recyclerView.invalidate();
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        mRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                user.clear();

                for (DocumentSnapshot document : documentSnapshots.getDocuments()) {

                    userData data = document.toObject(userData.class);

                    user.add(data);
                    adapter = new ViewAdapter(user);
                    recyclerView.setAdapter(adapter);
                    recyclerView.invalidate();
                    adapter.notifyDataSetChanged();
                }

            }
        });
    }
}
