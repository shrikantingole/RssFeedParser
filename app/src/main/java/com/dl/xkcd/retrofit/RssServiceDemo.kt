package com.dl.xkcd.retrofit

import com.ccpp.shared.domain.model.RssFeed
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


object RssServiceDemo {
    private const val BASE_URL = "https://xkcd.com/"
    private val builder = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(SimpleXmlConverterFactory.create())
    private val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClient = OkHttpClient.Builder()

    fun getRetrofit() {
        httpClient.addInterceptor(loggingInterceptor)
        builder.client(httpClient.build())
        val retrofit = builder.build()
        val rssService = retrofit.create<ApiClient>(ApiClient::class.java)
        val callAsync = rssService.feed
        callAsync.enqueue(object : Callback<RssFeed> {
            override fun onResponse(call: Call<RssFeed>?, response: Response<RssFeed>?) {
                if (response?.isSuccessful == true) {
                    val apiResponse: RssFeed? = response?.body()
                    // API response
                    println(apiResponse)
                } else {
                    println("XXXX Request Error :: " + response?.errorBody())
                }
            }

            override fun onFailure(call: Call<RssFeed>?, t: Throwable) {
                if (call?.isCanceled == true) {
                    println("XXXX Call was cancelled forcefully");
                } else {
                    println("XXXX Network Error :: " + t.localizedMessage);
                }
            }
        })
    }
}