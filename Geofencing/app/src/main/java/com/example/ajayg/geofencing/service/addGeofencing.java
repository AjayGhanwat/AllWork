package com.example.ajayg.geofencing.service;

import android.Manifest;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class addGeofencing extends Service implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    List<Geofence> mGeofences;
    Geofence mGeofence;
    GeofencingClient mGeofencingClient;
    GoogleApiClient mGoogleApiClient;
    LocationRequest locationRequest;
    private PendingIntent mGeofenceRequestIntent;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        locationRequest = LocationRequest.create()
                .setInterval(10000)
                .setFastestInterval(5000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        mGeofences = new ArrayList<>();

        mGeofencingClient = new GeofencingClient(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mGoogleApiClient.connect();

        createGeofences();

        return START_NOT_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        mGoogleApiClient.reconnect();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        int response = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if (response != ConnectionResult.SUCCESS) {
            showToast("" + response);
        }
    }

    public void createGeofences() {

        mGeofence = new Geofence.Builder()
                .setRequestId("1")
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                .setCircularRegion(19.050157, 72.918070, 15)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .build();

        mGeofences.add(mGeofence);
    }

    @Override
    public void onConnected(Bundle connectionHint) {

        mGeofenceRequestIntent = getGeofenceTransitionPendingIntent();
        try {
            mGeofenceRequestIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }

        grandPerMission();
    }

    private void grandPerMission() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            StartLocationMonitoring();
            mGeofencingClient.addGeofences(getGeofencingRequest(), getGeofenceTransitionPendingIntent())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            showToast("Added");
                        }
                    });
            LocationServices.GeofencingApi.addGeofences(mGoogleApiClient, getGeofencingRequest(), getGeofenceTransitionPendingIntent());
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, getGeofenceTransitionPendingIntent());
            showToast("Started");
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        showToast(" " + connectionResult.getErrorMessage());
    }

    @Override
    public void onConnectionSuspended(int i) {
        if (null != mGeofenceRequestIntent) {
            LocationServices.GeofencingApi.removeGeofences(mGoogleApiClient, mGeofenceRequestIntent);
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, getGeofenceTransitionPendingIntent());
        }
    }

    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER | GeofencingRequest.INITIAL_TRIGGER_EXIT);
        builder.addGeofences(mGeofences);
        return builder.build();
    }

    private PendingIntent getGeofenceTransitionPendingIntent() {
        Intent intent = new Intent(this, GeofenceTransitionsIntentService.class);
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void StartLocationMonitoring() {
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        showToast("Lat : " + location.getLatitude() + " \nLng : " + location.getLongitude());
                        if (ActivityCompat.checkSelfPermission(addGeofencing.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            LocationServices.GeofencingApi.addGeofences(mGoogleApiClient, getGeofencingRequest(), getGeofenceTransitionPendingIntent());
                            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, getGeofenceTransitionPendingIntent());
                        }
                    }
                });
            }

        } catch (Exception ex) {
            showToast("Exception in location Monitoring");
        }
    }

    private void showToast(final String msg) {
        Handler mainThread = new Handler(Looper.getMainLooper());
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                //  Toast.makeText(addGeofencing.this, "" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}