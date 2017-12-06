package com.bridgelabz.googlemapdemo;

import android.content.Context;
import android.location.Address;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class getDataToJson {

    RecyclerViewAdapter adapter;

    Context context;
    public static ArrayList<Datamodel> datamodels = new ArrayList<>();
    JSONArray getPhoto;

    public getDataToJson(Context context) {
        this.context = context;
    }

    public void getAllData( String url) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            datamodels.clear();

                            for (int i = 0; i < jsonArray.length(); i++){

                                JSONObject geometry = (JSONObject) jsonArray.getJSONObject(i).get("geometry");
                                JSONObject location = (JSONObject) geometry.getJSONObject("location");
                                Datamodel datamodel = new Datamodel();
                                datamodel.setLat(location.getDouble("lat"));
                                datamodel.setLng(location.getDouble("lng"));
                                datamodel.setId(String.valueOf(jsonArray.getJSONObject(i).get("id")));
                                datamodel.setLocationName(String.valueOf(jsonArray.getJSONObject(i).get("name")));
                                datamodel.setVicinity(String.valueOf(jsonArray.getJSONObject(i).get("vicinity")));
                                if (jsonArray.getJSONObject(i).has("rating"))
                                datamodel.setRating(String.valueOf(jsonArray.getJSONObject(i).get("rating")));
                                JSONObject photo = (JSONObject) jsonArray.get(i);
                                if (photo.has("photos")) {
                                    getPhoto = photo.getJSONArray("photos");
                                    JSONObject getPhotoRef = getPhoto.getJSONObject(0);
                                    datamodel.setPreference(String.valueOf(getPhotoRef.getString("photo_reference")));
                                }

                                datamodels.add(datamodel);
                            }

                            adapter = new RecyclerViewAdapter(context,datamodels);
                            DisplayLocations.recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

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
