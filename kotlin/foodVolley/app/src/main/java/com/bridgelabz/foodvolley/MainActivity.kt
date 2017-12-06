package com.bridgelabz.foodvolley

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Method

class MainActivity : AppCompatActivity() {

    val arrayData = ArrayList<dataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RecyclerViewFood.layoutManager = LinearLayoutManager(this)
        RecyclerViewFood.setHasFixedSize(true)

        var jsonReqQueue = Volley.newRequestQueue(this)

        var jsonReq = JsonArrayRequest(Request.Method.GET, "https://choco-lava.herokuapp.com/borrow/getfood", null,
                Response.Listener {
                    response ->  getAllData(response)
                }, Response.ErrorListener {
            error -> print(error)
        })

        jsonReqQueue.add(jsonReq)
    }

    private fun getAllData(response: JSONArray) {

        for( i in 0..response.length()-1 ){

            var jsonObl : JSONObject = response.get(i) as JSONObject
            val data = dataModel(jsonObl.getString("name"), jsonObl.getString("image"))
            arrayData.add(data)

        }
        RecyclerViewFood.adapter = myRecyclerAdapter(this, arrayData)
    }
}
