package com.acuon.inshorts

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.acuon.inshorts.model.NewsItem
import com.acuon.inshorts.model.NewsResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    var sliderItems = ArrayList<SliderItems>()
    var titles = ArrayList<String>()
    var desc = ArrayList<String>()
    var images = ArrayList<String>()
    var newslinks = ArrayList<String>()
    var heads = ArrayList<String>()

    /*
        val author: String,
    val content: String,
    val date: String,
    val id: String,
    val imageUrl: String,
    val readMoreUrl: String,
    val time: String,
    val title: String,
    val url: String
     */

    private lateinit var verticalViewPager: VerticalViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verticalViewPager = findViewById<View>(R.id.verticalViewPager) as VerticalViewPager
        makeApiCall()
    }

    private fun makeApiCall() {
        val apiService = Network.getRetrofitInstance().create(
            ApiService::class.java
        )
        val call: Call<NewsResponseBody?>? = apiService.getData("all")

        call?.enqueue(object : Callback<NewsResponseBody?> {
            override fun onResponse(
                call: Call<NewsResponseBody?>,
                response: Response<NewsResponseBody?>
            ) {
                val responseDTO: NewsResponseBody? = response.body()
                val dataDTOList: List<NewsItem?>? = responseDTO?.data

                dataDTOList?.forEach {
                    //add data to array list
                    titles.add(it?.title ?: "")
                    desc.add(it?.content ?: "")
                    images.add(it?.imageUrl ?: "")
                    newslinks.add(it?.readMoreUrl ?: "")
                    heads.add(it?.author ?: "")
                }

                images.forEach {
                    sliderItems.add(SliderItems(it))
                }

                verticalViewPager.adapter = ViewPagerAdapter(
                    this@MainActivity,
                    sliderItems,
                    titles,
                    desc,
                    newslinks,
                    heads,
                    verticalViewPager
                )

            }

            override fun onFailure(call: Call<NewsResponseBody?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failure", Toast.LENGTH_SHORT).show()
            }
        })
    }
}