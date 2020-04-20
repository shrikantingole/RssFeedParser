package com.ccpp.shared.network

import com.ccpp.shared.domain.model.RssFeed
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

internal interface ApiClient {


    @GET("rss.xml")
    fun callRssFeedAsync(): Deferred<Response<RssFeed>>

}