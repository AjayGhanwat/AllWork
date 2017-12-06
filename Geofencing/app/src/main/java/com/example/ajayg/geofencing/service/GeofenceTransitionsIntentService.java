package com.example.ajayg.geofencing.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.ajayg.geofencing.MainActivity;
import com.example.ajayg.geofencing.database.SQLiteDatabaseHandler;
import com.example.ajayg.geofencing.datamodel.AllData;
import com.example.ajayg.geofencing.datamodel.EnterExit;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GeofenceTransitionsIntentService extends Service {

    SQLiteDatabaseHandler sqLiteDatabaseHandler;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        onTaskRemoved(intent);
        sqLiteDatabaseHandler = new SQLiteDatabaseHandler(this);

        GeofencingEvent geoFenceEvent = GeofencingEvent.fromIntent(intent);
        if (geoFenceEvent.hasError()) {
            int errorCode = geoFenceEvent.getErrorCode();
            Log.e("Error", "Location Services error: " + errorCode);
        } else {
            int transitionType = geoFenceEvent.getGeofenceTransition();
            if (Geofence.GEOFENCE_TRANSITION_ENTER == transitionType) {
                EnterExit details = null;
                List<EnterExit> data = sqLiteDatabaseHandler.getAllRecordEnterExit();
                int count = data.size();

                if (!data.isEmpty())
                    details = data.get(data.size() - 1);
                if (count == 0 || details.getExit() != null) {
                    showToast("Enter");
                    Date currentTime = Calendar.getInstance().getTime();
                    String time = new SimpleDateFormat("hh:mm aaa").format(currentTime);

                    Date cDate = new Date();
                    String fDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);

                    showToast(time);
                    EnterExit enterExit = new EnterExit();

                    enterExit.setEnter(time);
                    enterExit.setExit(null);
                    enterExit.setInterval(null);
                    enterExit.setDate(fDate);

                    sqLiteDatabaseHandler.insertRecordEnterExit(enterExit);

                    if (MainActivity.activity) {
                        MainActivity.changeData();
                        MainActivity.changeAllData();
                    }
                    getInterval();
                }
                destroyData();
            } else if (Geofence.GEOFENCE_TRANSITION_EXIT == transitionType) {

                EnterExit enterExit = null;
                List<EnterExit> details = sqLiteDatabaseHandler.getAllRecordEnterExit();

                if (details.size() != 0) {
                    if (details.get(details.size() - 1).getExit() == null) {
                        showToast("Exit");
                        Date currentTime = Calendar.getInstance().getTime();
                        String time = new SimpleDateFormat("hh:mm aaa").format(currentTime);
                        showToast(time);

                        enterExit = details.get(details.size() - 1);

                        enterExit.setEnter(enterExit.getEnter());
                        enterExit.setExit(time);
                        enterExit.setInterval(enterExit.getInterval());
                        enterExit.setDate(enterExit.getDate());

                        sqLiteDatabaseHandler.updateRecordEnterExit(enterExit);
                        if (MainActivity.activity) {
                            MainActivity.changeData();
                            MainActivity.changeAllData();
                        }
                    }
                }
            }
        }
        return START_STICKY;
    }

    private void getInterval() {
        List<EnterExit> list = sqLiteDatabaseHandler.getAllRecordEnterExit();

        if (list != null && list.size() >= 2) {

            Date date1, date2;
            int days, hours, min;

            String mData1 = list.get(list.size() - 1).Enter;
            String mData2 = list.get(list.size() - 2).Exit;

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            try {
                date1 = simpleDateFormat.parse(mData1);
                date2 = simpleDateFormat.parse(mData2);

                long difference = date2.getTime() - date1.getTime();
                days = (int) (difference / (1000 * 60 * 60 * 24));
                hours = (int) ((difference - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
                min = (int) (difference - (1000 * 60 * 60 * 24 * days) - (1000 * 60 * 60 * hours)) / (1000 * 60);
                hours = (hours < 0 ? -hours : hours);

                List<EnterExit> details = sqLiteDatabaseHandler.getAllRecordEnterExit();
                EnterExit enterExit = details.get(details.size() - 1);

                enterExit.setEnter(enterExit.getEnter());
                enterExit.setExit(enterExit.getExit());
                enterExit.setInterval(Math.abs(hours) + " : " + Math.abs(min));
                enterExit.setDate(enterExit.getDate());

                sqLiteDatabaseHandler.updateRecordEnterExit(enterExit);
                if (MainActivity.activity) {
                    MainActivity.changeData();
                    MainActivity.changeAllData();
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not Yet Implemented!!!");
    }

    private void showToast(final String msg) {
        Handler mainThread = new Handler(Looper.getMainLooper());
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(GeofenceTransitionsIntentService.this, "" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {

        Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
        restartServiceIntent.setPackage(getPackageName());
        startService(restartServiceIntent);
        super.onTaskRemoved(rootIntent);
    }

    public void destroyData() {

        Date cDate = new Date();
        String fDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);

        ArrayList<EnterExit> data = sqLiteDatabaseHandler.getAllRecordEnterExit();

        if (data != null && !data.isEmpty()) {
            if (data.size() == 2 && !fDate.equals(data.get(data.size() - 2).getDate())) {

                sqLiteDatabaseHandler.deleteAllEnterExit();

                String currentDate = fDate;
                String firstEnter = data.get(0).getEnter();
                String lastExit = data.get(data.size() - 2).getExit();
                String interval = String.valueOf(data.size() - 2);

                AllData mAllData = new AllData();
                mAllData.setDate(currentDate);
                mAllData.setEnter(firstEnter);
                mAllData.setExit(lastExit);
                mAllData.setInterval(interval);

                sqLiteDatabaseHandler.insertRecordAllData(mAllData);

                EnterExit enterExit = new EnterExit();
                enterExit.setDate(data.get(data.size()-1).getDate());
                enterExit.setEnter(data.get(data.size()-1).getEnter());
                enterExit.setExit(data.get(data.size()-1).getExit());
                enterExit.setInterval(data.get(data.size()-1).getInterval());

                sqLiteDatabaseHandler.insertRecordEnterExit(enterExit);

                if (MainActivity.activity) {
                    MainActivity.changeAllData();
                    MainActivity.changeData();
                }
            }
        }
    }
}