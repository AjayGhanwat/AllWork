package com.bridgelabz.crickinfo.util;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bridgelabz.crickinfo.MySingleton.MySingleton;
import com.bridgelabz.crickinfo.model.matchDataModel;
import com.bridgelabz.crickinfo.view.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewMatches {

    static ArrayList<matchDataModel> list = new ArrayList<>();
    private static String url = "http://cricapi.com/api/matches/T4UZv8590thyRgJ5yUUrbc8d7Bp1";

    public static void getAllNewMatches(final Context context, final MainActivity mainActivity) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String toss_winner_team= "null";

                            JSONArray array = response.getJSONArray("matches");

                            for (int i = 0; i < array.length(); i++){

                                JSONObject object = (JSONObject) array.get(i);
                                int unique_id = object.getInt("unique_id");
                                String team_1 = object.getString("team-1");
                                String team_2 = object.getString("team-2");
                                String type = object.getString("type");
                                String dateTimeGMT = object.getString("dateTimeGMT");
                                if (object.has("toss_winner_team")) {
                                    toss_winner_team = object.getString("toss_winner_team");
                                }

                                matchDataModel dataModel = new matchDataModel(unique_id,type,team_1,team_2,toss_winner_team,dateTimeGMT);
                                list.add(dataModel);
                            }

                            mainActivity.setAllData(list);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
