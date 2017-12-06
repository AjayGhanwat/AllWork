package com.bridgelabz.googlemapdemo;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {

    private static MySingleton mySingleton;
    private RequestQueue requestQueue;
    private Context mCtx;

    public MySingleton(Context context){
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){

        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySingleton getMySingleton(Context context){

        if (mySingleton == null)
        mySingleton = new MySingleton(context);

        return mySingleton;
    }

    public<T> void addToRequestQueue(Request<T> request){

        requestQueue.add(request);

    }

}
