package com.bridgelabz.crickinfo.util;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bridgelabz.crickinfo.MySingleton.MySingleton;
import com.bridgelabz.crickinfo.adapter.currentMatchAdapter;
import com.bridgelabz.crickinfo.model.scoreDataModel;
import com.bridgelabz.crickinfo.view.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewMatchesScore {

    public static void getAllMatchScore(final Context context, int id, final MainActivity mainActivity){

        final ArrayList<scoreDataModel> list =new ArrayList<>();
        String url = "http://cricapi.com/api/cricketScore?unique_id="+id+"&apikey=T4UZv8590thyRgJ5yUUrbc8d7Bp1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String score = null;
                        try {
                            if (response.has("score")) {
                                score = response.getString("score");
                                list.add(new scoreDataModel(score));
                            }

                            //mainActivity.getAllScore(list);

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

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
