package com.example.pregnancy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pregnancy.Adapters.PregnancyArticlesAdapter
import com.example.pregnancy.dataModels.Feed
import com.example.pregnancy.dataModels.RssFeed
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_pregnancy__articles.*
import okhttp3.*
import java.io.IOException

class Pregnancy_Articles : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregnancy__articles)



        pregnancy_articles_recyclerView.layoutManager=LinearLayoutManager(this)




        fetchData()



    }

    private fun fetchData(){


        val url="https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fwww.pregnancymagazine.com%2Ffeed"

        val client =OkHttpClient()

        val request =Request.Builder().url(url).build()

        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {

                val body= response.body()?.string()

                println("Printing Body........")

                println(body)

                val gson=GsonBuilder().create()

                val homeFeed=gson.fromJson(body, RssFeed::class.java)

                runOnUiThread { pregnancy_articles_recyclerView.adapter=PregnancyArticlesAdapter(homeFeed, this@Pregnancy_Articles) }







            }
        })

    }
}
