package com.bridgelabz.samplesqlitedatabase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Broadcastreceiver extends BroadcastReceiver {

    CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("Data");

    @Override
    public void onReceive(Context context, Intent intent) {

        if (!isOnline(context)) {

            final SQLiteDatabaseHelper sqLiteDatabaseHelper = new SQLiteDatabaseHelper(context);

            collectionReference = FirebaseFirestore.getInstance().collection("Data");

            collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot documentSnapshots) {

                    for (DocumentSnapshot post : documentSnapshots.getDocuments()){

                        DataModel dataModel = post.toObject(DataModel.class);

                        sqLiteDatabaseHelper.insertRecord(dataModel);

                    }
                }
            });

        }
    }

    private boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}
