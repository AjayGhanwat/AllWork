package com.bridgelabz.retriverdata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private RecyclerView re;
    private List<UserModul> result;
    private UserViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Hello");


        result = new ArrayList<>();

        re = (RecyclerView) findViewById(R.id.noteRecyclerView);
        re.setHasFixedSize(true);
        LinearLayoutManager li = new LinearLayoutManager(this);
        li.setOrientation(LinearLayoutManager.VERTICAL);

        re.setLayoutManager(li);

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<UserModul, userViewHolde> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<UserModul, userViewHolde>(

                UserModul.class,
                R.layout.view_item,
                userViewHolde.class,
                reference

        ) {
            @Override
            protected void populateViewHolder(userViewHolde viewHolder, UserModul model, int position) {

                viewHolder.setmTitle(model.getmTitle());
                viewHolder.setmDesc(model.getmDesc());

            }
        };

        re.setAdapter(firebaseRecyclerAdapter);
    }

    public static class userViewHolde extends RecyclerView.ViewHolder {

        View mView;

        public userViewHolde(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setmTitle(String title) {

            TextView user_title = (TextView) mView.findViewById(R.id.noteTitle);
            user_title.setText(title);
        }

        public void setmDesc(String desc) {

            TextView user_desc = (TextView) mView.findViewById(R.id.noteDesc);
            user_desc.setText(desc);
        }
    }
}
