package com.bridgelabz.fundoopay.home.view;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bridgelabz.fundoopay.R;
import com.bridgelabz.fundoopay.base.BaseFragment;
import com.bridgelabz.fundoopay.home.homeAdapter.notificationAdapter;
import com.bridgelabz.fundoopay.home.model.notifications;

import java.util.ArrayList;

public class usersNotification extends BaseFragment {

    View v;
    RecyclerView recyclerView;

    Toolbar mainToolbar;
    TextView fundoo, pay, addAccount;
    ImageButton navigationIcon;

    notifications notifications;
    RecyclerView.Adapter notificationAdapter;
    ArrayList<notifications> mNotifications;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        return inflater.inflate(R.layout.usernotification, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v = view;

        initView();
        initToolbarView();
        clickListner();

        navigationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View ve) {

                getActivity().getFragmentManager().popBackStack();

            }
        });

        getNotifications();

    }

    private void getNotifications() {

        mNotifications = new ArrayList<>();
        notifications = new notifications("Rs.200 Paid for NATARAJ (Super Market)", "23rd July’17", "20:30");
        mNotifications.add(notifications);
        notifications = new notifications("Rs.90 Paid for CleanNclear (Laundry)", "20th July’17", "22:37");
        mNotifications.add(notifications);
        notifications = new notifications("Rs.554 Due for NATARAJ (Super Market) from last 7 days.", "19th July’17", "14:11");
        mNotifications.add(notifications);
        notificationAdapter = new notificationAdapter(mNotifications);
        recyclerView.setAdapter(notificationAdapter);
        notificationAdapter.notifyDataSetChanged();

    }

    @Override
    public void initView() {

        recyclerView = (RecyclerView) v.findViewById(R.id.notificationUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

    }


    @Override
    public void initToolbarView() {

        mainToolbar = (Toolbar) v.findViewById(R.id.toolbar);

        fundoo = (TextView) v.findViewById(R.id.fundoo);
        pay = (TextView) v.findViewById(R.id.pay);
        addAccount = (TextView) v.findViewById(R.id.addAccount);
        navigationIcon = (ImageButton) v.findViewById(R.id.navigationicon);

    }

    @Override
    public void clickListner() {

    }

    @Override
    public void onResume() {

        fundoo.setVisibility(View.GONE);
        pay.setVisibility(View.GONE);
        navigationIcon.setVisibility(View.VISIBLE);
        addAccount.setVisibility(View.VISIBLE);
        addAccount.setText("Notifications");
        mainToolbar.setVisibility(View.VISIBLE);

        super.onResume();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);


        if (isVisibleToUser) {

            fundoo.setVisibility(View.GONE);
            pay.setVisibility(View.GONE);
            navigationIcon.setVisibility(View.VISIBLE);
            addAccount.setVisibility(View.VISIBLE);
            addAccount.setText("Notifications");
            mainToolbar.setVisibility(View.VISIBLE);

        }
    }
}
