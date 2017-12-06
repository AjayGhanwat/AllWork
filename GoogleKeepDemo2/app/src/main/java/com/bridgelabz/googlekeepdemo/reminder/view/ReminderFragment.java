package com.bridgelabz.googlekeepdemo.reminder.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bridgelabz.googlekeepdemo.R;
import com.bridgelabz.googlekeepdemo.base.BaseFragment;
import com.bridgelabz.googlekeepdemo.note.model.noteData;
import com.bridgelabz.googlekeepdemo.note.adapter.noteAdapter;
import com.bridgelabz.googlekeepdemo.reminder.adapter.reminderAdapter;
import com.bridgelabz.googlekeepdemo.reminder.model.reminderData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ReminderFragment extends BaseFragment{

    View v;

    RecyclerView mReminderRecycler;

    ArrayList<reminderData> reminderDataList;
    reminderData reminderData;
    reminderAdapter reminderAdapter;

    CollectionReference myDoc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_reminder, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        v = view;

        String userID = FirebaseAuth.getInstance().getUid();

        myDoc = FirebaseFirestore.getInstance().collection("Notes").document(userID).collection("Note");

        initView();
        clickListning();

        getAllDataToDisplay();
    }

    private void getAllDataToDisplay() {

        myDoc.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                reminderDataList.clear();

                if (documentSnapshots != null) {

                    for (DocumentSnapshot documents : documentSnapshots.getDocuments()) {

                        reminderData = documents.toObject(reminderData.class);

                        if (reminderData.getLocation().equals("reminder")) {
                            reminderDataList.add(reminderData);
                        }
                        reminderAdapter = new reminderAdapter(reminderDataList);
                        mReminderRecycler.setAdapter(reminderAdapter);
                        reminderAdapter.notifyDataSetChanged();
                    }
                }
                mReminderRecycler.invalidate();
            }
        });
    }

    @Override
    public void initView() {
        mReminderRecycler = (RecyclerView) v.findViewById(R.id.recyclerReminder);
        mReminderRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mReminderRecycler.setHasFixedSize(true);

        reminderDataList = new ArrayList<>();
    }

    @Override
    public void clickListning() {

    }
}
