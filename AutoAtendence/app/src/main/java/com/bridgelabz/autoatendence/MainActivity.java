package com.bridgelabz.autoatendence;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    public static TextView textView;
    private GeofencingClient mGeofencingClient;

    List<Geofence> mGeofenceList;
    private LocationServices mLocationServices;
    private PendingIntent mGeoFencingRequestIntent;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGeofencingClient = LocationServices.getGeofencingClient(this);

        textView = (TextView) findViewById(R.id.hello);

        if (!GooglePlayServicesAvailable()) {
            finish();
            return;
        }

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mGoogleApiClient.connect();

        mGeofenceList = new ArrayList<Geofence>();

        createGeoFences();
    }

    public void createGeoFences() {

        Geofence mGeofence = new Geofence.Builder()
                .setRequestId("1")
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_DWELL | Geofence.GEOFENCE_TRANSITION_EXIT)
                .setLoiteringDelay(3000)
                .setCircularRegion(19.050316, 72.91803, 50.0F)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .build();

        mGeofenceList.add(mGeofence);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            LocationRequest locationRequest = LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setFastestInterval(500L).setInterval(100L)
                    .setSmallestDisplacement(75.0F);

            mGeoFencingRequestIntent = getGeofenceTransitionPendingIntent();
            //LocationServices.GeofencingApi.addGeofences(mGoogleApiClient, mGeofenceList, mGeoFencingRequestIntent);

            /*try {
                mGeoFencingRequestIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }*/
            //finish();

            mGeofencingClient.addGeofences(getGeofencingRequest(), getGeofenceTransitionPendingIntent())
                    .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            textView.setText("Add!!");
                        }
                    })
                    .addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            textView.setText("Error");
                        }
                    });
            LocationServices.GeofencingApi.addGeofences(mGoogleApiClient, mGeofenceList, mGeoFencingRequestIntent);
            //LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest,mGeoFencingRequestIntent);
            textView.setText("Connection Started!!");

        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        if (null!= mGeoFencingRequestIntent){
            LocationServices.GeofencingApi.removeGeofences(mGoogleApiClient, mGeoFencingRequestIntent);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        if (connectionResult.hasResolution()){

            try {
                connectionResult.startResolutionForResult(this, 9000);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        }else{

            int errorCode = connectionResult.getErrorCode();
            Toast.makeText(this, "Faild To Connet To google Play Services with error Code : " + errorCode, Toast.LENGTH_SHORT).show();

        }
    }

    private boolean GooglePlayServicesAvailable(){
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        return ConnectionResult.SUCCESS == resultCode;
    }

    private PendingIntent getGeofenceTransitionPendingIntent(){
        Intent intent = new Intent(this, GeofenceTransitionsIntentService.class);
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(mGeofenceList);
        return builder.build();
    }
}
