package com.bridgelabz.autoatendence;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

public class GeofenceTransitionsIntentService extends IntentService implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    public GeofenceTransitionsIntentService() {
        super(GeofenceTransitionsIntentService.class.getSimpleName());
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        GeofencingEvent mGeofencingEvent = GeofencingEvent.fromIntent(intent);
        assert mGeofencingEvent != null;
        if (mGeofencingEvent.hasError()){

            Toast.makeText(this, ""+ mGeofencingEvent.getErrorCode(), Toast.LENGTH_SHORT).show();
            
        }else{

            int trasitionType = mGeofencingEvent.getGeofenceTransition();

            if(Geofence.GEOFENCE_TRANSITION_ENTER == trasitionType){
                showToast("Entered In GeoFencing");
            }else if(Geofence.GEOFENCE_TRANSITION_EXIT == trasitionType){
                showToast("geoFencing Location Exited");
            }else if(Geofence.GEOFENCE_TRANSITION_DWELL == trasitionType){
                showToast("You are in geoFencing Location");
            }else {
                showToast(" " + trasitionType);
            }

        }

    }

    private void showToast(final String resource) {
        Handler mainThread = new Handler(Looper.getMainLooper());
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                String msg = MainActivity.textView.getText().toString();
                String new_msg = msg + " \n " + resource;
                MainActivity.textView.setText(new_msg);
            }
        });
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
