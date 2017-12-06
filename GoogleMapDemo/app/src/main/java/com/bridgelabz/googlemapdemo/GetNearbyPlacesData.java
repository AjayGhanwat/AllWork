package com.bridgelabz.googlemapdemo;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetNearbyPlacesData extends AsyncTask<Object, String, String> {

    String googlePLaceData;
    GoogleMap mMap;
    String url;

    @Override
    protected String doInBackground(Object... objects) {

        mMap = (GoogleMap) objects[0];
        url = (String) objects[1];

        DownloadURL downloadURL = new DownloadURL();
        try {
            googlePLaceData = downloadURL.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googlePLaceData;
    }

    @Override
    protected void onPostExecute(String s) {
        List<HashMap<String, String>> nearPlacesList = null;
        DataParser parser = new DataParser();
        nearPlacesList = parser.parse(s);
        showNearbyPlaces(nearPlacesList);
    }

    private void showNearbyPlaces(List<HashMap<String, String>> nearbyPlaceList) {

        for (int i = 0; i < nearbyPlaceList.size(); i++){
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlaces = nearbyPlaceList.get(i);

            String placeName = googlePlaces.get("placeName");
            String vicinity = googlePlaces.get("vicinity");
            String lat = googlePlaces.get("lat");
            String lng = googlePlaces.get("lng");

            LatLng latLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
            markerOptions.position(latLng);
            markerOptions.title(placeName +" : "+ vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
            markerOptions.flat(true);
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(13));

        }

    }
}
