package com.acuon.inshorts

import com.acuon.inshorts.model.NewsResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/news")
    fun getData(@Query("category") category: String?): Call<NewsResponseBody?>?
}