package com.bridgelabz.googlenews

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var list = ArrayList<dataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsRecycler.layoutManager = LinearLayoutManager(this)
        newsRecycler.setHasFixedSize(true)

        var jsonReqQue = Volley.newRequestQueue(this);
        var jsonReq = JsonObjectRequest(Request.Method.GET, "https://newsapi.org/v1/articles?source=abc-news-au&sortBy=top&apiKey=6e52db0dbbc04f888b2dbe9a3c801a49", null,
                Response.Listener { response ->
                    list.clear()
                    getAllData(response)
                }, Response.ErrorListener { error ->
            Toast.makeText(this, " " + error, Toast.LENGTH_LONG).show()
        })

        jsonReqQue.add(jsonReq)
    }

    private fun getAllData(response: JSONObject) {

        var jsonArray = response.getJSONArray("articles")

        for (j in 0..jsonArray.length() - 1) {

            var articalJsonObj = jsonArray.getJSONObject(j)

            var data = dataModel(articalJsonObj.getString("title"), articalJsonObj.getString("description"), articalJsonObj.getString("urlToImage"), articalJsonObj.getString("publishedAt"), articalJsonObj.getString("url"))

            list.add(data)

        }

        newsRecycler.adapter = newsRecyclerAdapter(this, list)

    }

}
