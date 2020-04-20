package com.ccpp.shared.network

import com.ccpp.shared.BuildConfig
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ApiService @Inject constructor(
    builder: Retrofit.Builder
) {


    private val apiClient =
        builder.baseUrl(BuildConfig.baseUrl)
            .build().create(ApiClient::class.java)


    fun callRssFeedAsync() =
        apiClient.callRssFeedAsync()


}
