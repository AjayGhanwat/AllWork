package com.bridgelabz.crickinfo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import com.bridgelabz.crickinfo.R;
import com.bridgelabz.crickinfo.adapter.currentMatchAdapter;
import com.bridgelabz.crickinfo.model.matchDataModel;
import com.bridgelabz.crickinfo.model.scoreDataModel;
import com.bridgelabz.crickinfo.util.NewMatches;
import com.bridgelabz.crickinfo.util.NewMatchesScore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static RecyclerView matchRecyclerView;
    static currentMatchAdapter matchAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matchRecyclerView = (RecyclerView) findViewById(R.id.currentMatches);
        matchRecyclerView.setHasFixedSize(true);
        matchRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        NewMatches.getAllNewMatches(this, this);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(matchRecyclerView);
    }

    public void setAllData(ArrayList<matchDataModel> list) {
        matchAdapter = new currentMatchAdapter(this,list);
        matchRecyclerView.getLayoutManager().scrollToPosition(0);
        matchRecyclerView.setAdapter(matchAdapter);
        matchAdapter.notifyDataSetChanged();
    }

}
