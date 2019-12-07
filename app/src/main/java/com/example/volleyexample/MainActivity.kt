package com.example.volleyexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {
    private val JSON_URL =
        "https://gist.githubusercontent.com/Alewitox/452c2dc7112ba33313cf32cda8efd9b2/raw/03e6cf772f07c5db8cb683cd40bab8caf0b731af/animelist"
    private var request: JsonArrayRequest? = null
    private var requestQueue: RequestQueue? = null
    private var lstAnime: MutableList<Anime>? = null
    private var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lstAnime = ArrayList()
        recyclerView = findViewById(R.id.recyclerviewid)
        jsonrequest()
    }

    fun jsonrequest() {
        request =
            JsonArrayRequest(JSON_URL,
                Response.Listener { response ->
                    var jsonObject: JSONObject?
                    for (i in 0 until response.length()) {
                        try {
                            jsonObject = response.getJSONObject(i)
                            val anime = Anime()
                            anime.name = jsonObject.getString("name")
                            anime.rating = jsonObject.getString("Rating")
                            anime.description = jsonObject.getString("description")
                            anime.image_url = jsonObject.getString("img")
                            anime.studio = jsonObject.getString("studio")
                            anime.categorie = jsonObject.getString("categorie")
                            lstAnime!!.add(anime)
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                    setuprecyclerview(lstAnime)
                }, Response.ErrorListener { })
        requestQueue = Volley.newRequestQueue(this@MainActivity)
        requestQueue!!.add(request)
    }

    fun setuprecyclerview(lstAnime: List<Anime>?) {
        val myadapter = RecyclerViewAdapter(this, lstAnime!!)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.adapter = myadapter
    }
}