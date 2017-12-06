package com.bridgelabz.util.notificationbuilder;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.*;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.TaskStackBuilder;

public class MainActivity extends AppCompatActivity {

    private NotificationManager mNotificationManager;
    private int notificationID = 100;
    private int numMassage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startNotification = (Button) findViewById(R.id.startNotification);
        startNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNotification();
            }
        });

        Button cancelNotification = (Button) findViewById(R.id.cancelNotification);
        cancelNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelNotification();
            }
        });

        Button updateNotification = (Button) findViewById(R.id.updateNotification);
        updateNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNotification();
            }
        });

    }

    private void startNotification() {

        Log.d("msg", "StartedNotification");

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

        mBuilder.setContentTitle("New Message");
        mBuilder.setContentText("you Have Recived New Message");
        mBuilder.setTicker("New Messaage alert!");
        mBuilder.setSmallIcon(R.drawable.icon);

        mBuilder.setNumber(++numMassage);

        Intent resultIntent = new Intent(this, Notification.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(Notification.class);

        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);

        mNotificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

            mNotificationManager.notify(notificationID, mBuilder.build());

    }

    private void cancelNotification() {
        Log.d("msg", "notification");

        mNotificationManager.cancel(notificationID);
    }

    private void updateNotification() {

        Log.d("Update", "notification");

        NotificationCompat.Builder mBuilder =new NotificationCompat.Builder(this);

        mBuilder.setContentTitle("Updated Message");
        mBuilder.setContentText("You've got updated message.");
        mBuilder.setTicker("Updated Message Alert!");
        mBuilder.setSmallIcon(R.drawable.icon);

        mBuilder.setNumber(++numMassage);

        Intent resultIntent = new Intent(this, Notification.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        stackBuilder.addParentStack(Notification.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);

        mNotificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(notificationID, mBuilder.build());
    }
}
