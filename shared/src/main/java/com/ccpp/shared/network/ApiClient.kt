package com.ccpp.shared.network

import kotlinx.coroutines.Deferred
import me.toptas.rssconverter.RssFeed
import retrofit2.Response
import retrofit2.http.GET

internal interface ApiClient {


    @GET("rss.xml")
    fun callRssFeedAsync(): Deferred<Response<RssFeed>>

}