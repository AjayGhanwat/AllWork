package com.example.ajayg.geofencing.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.ajayg.geofencing.service.GeofenceTransitionsIntentService;
import com.example.ajayg.geofencing.service.addGeofencing;
import com.google.android.gms.location.GeofencingRequest;

public class startServices extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            Toast.makeText(context, "Boot completed", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(context, addGeofencing.class);
            context.startService(intent1);
            Intent intent2 = new Intent(context, GeofenceTransitionsIntentService.class);
            context.startService(intent2);
        }
    }
}
