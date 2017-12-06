package com.bridgelabz.googlekeepdemo.archive.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bridgelabz.googlekeepdemo.R;
import com.bridgelabz.googlekeepdemo.archive.adapter.archiveAdapter;
import com.bridgelabz.googlekeepdemo.archive.model.archiveData;
import com.bridgelabz.googlekeepdemo.base.BaseFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ArchiveFragment extends BaseFragment {

    View v;

    RecyclerView mArchiveRecycler;

    ArrayList<archiveData> archiveDataList;
    archiveData archiveData;
    archiveAdapter archiveAdapter;

    CollectionReference myDoc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_archive, container, false);
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

                archiveDataList.clear();

                if (documentSnapshots != null) {

                    for (DocumentSnapshot documents : documentSnapshots.getDocuments()) {

                        archiveData = documents.toObject(archiveData.class);

                        if (archiveData.getLocation().equals("archive")) {
                            archiveDataList.add(archiveData);
                        }
                        archiveAdapter = new archiveAdapter(archiveDataList);
                        mArchiveRecycler.setAdapter(archiveAdapter);
                        archiveAdapter.notifyDataSetChanged();
                    }
                }
                mArchiveRecycler.invalidate();
            }
        });
    }

    @Override
    public void initView() {
        mArchiveRecycler = (RecyclerView) v.findViewById(R.id.unpinNotes);
        mArchiveRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mArchiveRecycler.setHasFixedSize(true);

        archiveDataList = new ArrayList<>();
    }

    @Override
    public void clickListning() {

    }
}
