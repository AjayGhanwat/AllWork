package com.bridgelabz.nearplaces;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetToJSONArea {

    public static ArrayList<String> datamodels = new ArrayList<>();
    public static ArrayList<DataModel> wholeData = new ArrayList<>();
    static Context context;

    public GetToJSONArea(Context context) {
        this.context = context;
    }

    public static void getList(double currentLatitude, double currentLongitude) {

        for (int i = 1000; i < 4000; i = i + 500) {

            String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+currentLatitude+","+currentLongitude+"&radius=" + i + "&type=sublocality_level_1&sensor=true&key=AIzaSyABpsXU1PTjgxdWsboX8JzRwX-3sKPRRbY";

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                JSONArray jsonArray = response.getJSONArray("results");

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject geometry = (JSONObject) jsonArray.getJSONObject(i).get("geometry");
                                    JSONObject location = (JSONObject) geometry.getJSONObject("location");
                                    DataModel datamodel = new DataModel();
                                    datamodel.setLat(location.getDouble("lat"));
                                    datamodel.setLng(location.getDouble("lng"));
                                    datamodel.setId(String.valueOf(jsonArray.getJSONObject(i).get("id")));
                                    datamodel.setLocationName(String.valueOf(jsonArray.getJSONObject(i).get("name")));
                                    datamodel.setVicinity(String.valueOf(jsonArray.getJSONObject(i).get("vicinity")));

                                    if (!datamodels.contains(datamodel.getLocationName())) {
                                        datamodels.add(datamodel.getLocationName());
                                        wholeData.add(datamodel);
                                        Log.i("responce", "onResponse: " + wholeData);
                                    }
                                    Log.i("responce", "onResponse: " + datamodel.getLocationName());
                                }

                                ArrayAdapter aa = new ArrayAdapter(context, android.R.layout.simple_spinner_item, datamodels);

                                MainActivity.spinner.setAdapter(aa);

                                Log.i("responce", "onResponse: " + response);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "" + error, Toast.LENGTH_SHORT).show();
                }
            });

            MySingleton.getMySingleton(context).addToRequestQueue(jsonObjectRequest);

        }

    }
}
