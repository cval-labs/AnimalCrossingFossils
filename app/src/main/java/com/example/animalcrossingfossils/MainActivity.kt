package com.example.animalcrossingfossils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.ImageView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var fossilList: MutableList<Triple<String,String,String>>
    private lateinit var rvFossils: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fossilList = arrayListOf()
        rvFossils = findViewById(R.id.fossil_list)


        getFossil()
    }



    private fun getFossil() {
        val client = AsyncHttpClient()

        client["https://acnhapi.com/v1a/fossils/", object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.d("Fossil Error", "error")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.d("Fossil", "success $json")
                val fossilArray = json.jsonArray



                for (i in 0 until fossilArray.length()) {
                    val image = fossilArray.getJSONObject(i).getString("image_uri")
                    val name = fossilArray.getJSONObject(i).getJSONObject("name").getString("name-USen").replaceFirstChar { it.titlecase() }
                    val price = fossilArray.getJSONObject(i).getString("price")


                    Log.d("AC Image","$i, $image")
                    Log.d("AC Name","$i, $name")
                    Log.d("AC Image","$i, $price")
                    fossilList.add(Triple(image, name, price))





               }


                val adapter = FossilAdapter(fossilList)
                rvFossils.adapter = adapter
                rvFossils.layoutManager = LinearLayoutManager(this@MainActivity)
                rvFossils.addItemDecoration(DividerItemDecoration(this@MainActivity,LinearLayoutManager.VERTICAL))
            }

        }]
    }

}