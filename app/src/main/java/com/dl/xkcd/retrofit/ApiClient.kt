package com.dl.xkcd.retrofit

import com.ccpp.shared.domain.model.RssFeed
import retrofit2.Call
import retrofit2.http.GET


interface ApiClient {
    @get:GET("rss.xml")
    val feed: Call<RssFeed>
}