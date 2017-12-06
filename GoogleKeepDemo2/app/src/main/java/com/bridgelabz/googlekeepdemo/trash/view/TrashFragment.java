package com.bridgelabz.googlekeepdemo.trash.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bridgelabz.googlekeepdemo.R;
import com.bridgelabz.googlekeepdemo.base.BaseFragment;
import com.bridgelabz.googlekeepdemo.trash.adapter.trashAdapter;
import com.bridgelabz.googlekeepdemo.trash.model.trashData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class TrashFragment extends BaseFragment{

    View v;

    RecyclerView mTrashRecycler;

    ArrayList<trashData> trashDataList;
    trashData trashData;
    trashAdapter trashAdapter;

    CollectionReference myDoc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_trash, container, false);
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

                trashDataList.clear();

                if (documentSnapshots != null) {

                    for (DocumentSnapshot documents : documentSnapshots.getDocuments()) {

                        trashData = documents.toObject(trashData.class);

                        if (trashData.getLocation().equals("trash")) {
                            trashDataList.add(trashData);
                        }
                        trashAdapter = new trashAdapter(trashDataList);
                        mTrashRecycler.setAdapter(trashAdapter);
                        trashAdapter.notifyDataSetChanged();
                    }
                }
                mTrashRecycler.invalidate();
            }
        });
    }

    @Override
    public void initView() {
        mTrashRecycler = (RecyclerView) v.findViewById(R.id.recyclerTrash);
        mTrashRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTrashRecycler.setHasFixedSize(true);

        trashDataList = new ArrayList<>();
    }

    @Override
    public void clickListning() {

    }
}
