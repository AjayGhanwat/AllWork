package com.bridgelabz.googlekeepdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Note extends Fragment {

    DatabaseReference mRef;
    FirebaseAuth mAuth;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_note, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Note");

        recyclerView = (RecyclerView) view.findViewById(R.id.noteRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAuth = FirebaseAuth.getInstance();

        String userId = mAuth.getCurrentUser().getUid();

        Date cDate = new Date();
        String fDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);

        mRef = FirebaseDatabase.getInstance().getReference().child("data").child(userId);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.addNote);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Add.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public static class userViewHolde extends RecyclerView.ViewHolder {

        View mView;

        public userViewHolde(View itemView) {
            super(itemView);

            this.mView = itemView;
        }

        public void setTitle(String title) {

            TextView user_title = (TextView) mView.findViewById(R.id.titleCard);
            user_title.setText(title);
        }

        public void setNote(String desc) {

            TextView user_desc = (TextView) mView.findViewById(R.id.descCard);
            user_desc.setText(desc);
        }

        public void setId(String id) {

           // TextView user_desc = (TextView) mView.findViewById(R.id.descCard);
           // user_desc.setText(id);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        Date cDate = new Date();
        String fDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);

        FirebaseRecyclerAdapter<DataModel, userViewHolde> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DataModel, userViewHolde>(

                DataModel.class,
                R.layout.noncheck_list,
                userViewHolde.class,
                mRef.child(fDate).child("0")

        ) {
            @Override
            protected void populateViewHolder(userViewHolde viewHolder, DataModel model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setNote(model.getNote());
                viewHolder.setNote(model.getId());

            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

}
