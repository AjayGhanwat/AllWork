package com.bridgelabz.googlemapdemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;

    static String url;

    EditText changeType;
    Button getAllData, hospital, restaurant, school;

    EditText searchLocation;
    Button searchPlace, nearPlaceHospital, nearPlaceRestaurant, nearPlaceSchool;

    int PROXIMITY_RADIUS = 500;
    double latitude, longitude;
    double mLatitudePlace, mLongitudeplace;

    GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        getAllData = (Button) findViewById(R.id.getData);
        changeType = (EditText) findViewById(R.id.changeType);
        hospital = (Button) findViewById(R.id.nearByHospital);
        restaurant = (Button) findViewById(R.id.nearByRestaurants);
        school = (Button) findViewById(R.id.nearBySchool);

        searchLocation = (EditText) findViewById(R.id.searchPlace);
        searchPlace = (Button) findViewById(R.id.searchLocation);
        nearPlaceHospital = (Button) findViewById(R.id.nearByPlaceHospital);
        nearPlaceRestaurant = (Button) findViewById(R.id.nearByPlaceRestaurants);
        nearPlaceSchool = (Button) findViewById(R.id.nearByPlaceSchool);

        getAllData.setOnClickListener(this);
        hospital.setOnClickListener(this);
        restaurant.setOnClickListener(this);
        school.setOnClickListener(this);
        searchPlace.setOnClickListener(this);
        nearPlaceHospital.setOnClickListener(this);
        nearPlaceRestaurant.setOnClickListener(this);
        nearPlaceSchool.setOnClickListener(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED) {
            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},2);
            }

        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mMap.setMyLocationEnabled(true);

            mMap.setOnMyLocationChangeListener(myLocationChangeListener);

            LatLng sydney = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(sydney).title("ME"));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    }

    @Override
    public void onClick(View view) {

        Object dataTrasfer[] = new Object[2];
        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();

        switch (view.getId()){

            case R.id.getData :

                getDataToJson dataToJson = new getDataToJson(this);
                dataToJson.getAllData(url);
                startActivity(new Intent(this, DisplayLocations.class));

                break;

            case R.id.nearByHospital :

                if (!TextUtils.isEmpty(changeType.getText()))
                    PROXIMITY_RADIUS = Integer.parseInt(changeType.getText().toString());

                mMap.clear();
                String hospital = "hospital";
                url = getUrl(latitude, longitude, hospital);
                dataTrasfer[0] = mMap;
                dataTrasfer[1] = url;

                getNearbyPlacesData.execute(dataTrasfer);

                break;

            case R.id.nearByRestaurants :

                if (!TextUtils.isEmpty(changeType.getText()))
                PROXIMITY_RADIUS = Integer.parseInt(changeType.getText().toString());

                mMap.clear();
                String restaurant = "restaurant";
                url = getUrl(latitude, longitude, restaurant);
                dataTrasfer[0] = mMap;
                dataTrasfer[1] = url;

                getNearbyPlacesData.execute(dataTrasfer);

                break;

            case R.id.nearBySchool :

                if (!TextUtils.isEmpty(changeType.getText()))
                    PROXIMITY_RADIUS = Integer.parseInt(changeType.getText().toString());

                mMap.clear();
                String school = "school";
                url = getUrl(latitude, longitude, school);
                dataTrasfer[0] = mMap;
                dataTrasfer[1] = url;

                getNearbyPlacesData.execute(dataTrasfer);

                break;

            case R.id.searchLocation :

                List<Address> addressesList = null;
                String location = searchLocation.getText().toString();

                if (location!= null && !location.equals("")){

                    mMap.clear();

                    Geocoder geocoder = new Geocoder(this);
                    try {
                        addressesList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Address address = addressesList.get(0);
                    mLatitudePlace = address.getLatitude();
                    mLongitudeplace = address.getLongitude();
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng));
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(10));

                }

                break;

            case R.id.nearByPlaceHospital :

                if (!TextUtils.isEmpty(changeType.getText()))
                    PROXIMITY_RADIUS = Integer.parseInt(changeType.getText().toString());

                mMap.clear();
                hospital = "hospital";
                url = getUrl(mLatitudePlace, mLongitudeplace, hospital);
                dataTrasfer[0] = mMap;
                dataTrasfer[1] = url;

                getNearbyPlacesData.execute(dataTrasfer);

                break;

            case R.id.nearByPlaceRestaurants :

                if (!TextUtils.isEmpty(changeType.getText()))
                    PROXIMITY_RADIUS = Integer.parseInt(changeType.getText().toString());

                mMap.clear();
                restaurant = "restaurant";
                url = getUrl(mLatitudePlace, mLongitudeplace, restaurant);
                dataTrasfer[0] = mMap;
                dataTrasfer[1] = url;

                getNearbyPlacesData.execute(dataTrasfer);

                break;

            case R.id.nearByPlaceSchool :

                if (!TextUtils.isEmpty(changeType.getText()))
                    PROXIMITY_RADIUS = Integer.parseInt(changeType.getText().toString());

                mMap.clear();
                school = "school";
                url = getUrl(mLatitudePlace, mLongitudeplace, school);
                dataTrasfer[0] = mMap;
                dataTrasfer[1] = url;

                getNearbyPlacesData.execute(dataTrasfer);

                break;

        }
    }

    private String getUrl(double latitude, double longitude, String nearbyPlace){

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location="+latitude+","+longitude);
        googlePlacesUrl.append("&radius="+PROXIMITY_RADIUS);
        googlePlacesUrl.append("&type=colloquial_area");
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key="+"AIzaSyDPG_iW97-O3hMDM4a9Heb0q6gvuEa9Fmo");

        Log.i("JSONData", "getUrl: "+googlePlacesUrl.toString());

        return googlePlacesUrl.toString();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1 : {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
                    onMapReady(mMap);

                } else {
                    Toast.makeText(this, "Not Granted", Toast.LENGTH_SHORT).show();
                }
            }

            case 2 : {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
                    onMapReady(mMap);

                } else {
                    Toast.makeText(this, "Not Granted", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
