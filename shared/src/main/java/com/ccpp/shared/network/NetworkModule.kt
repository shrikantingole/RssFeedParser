package com.ccpp.shared.network

import com.ccpp.shared.BuildConfig
import com.ccpp.shared.core.base.BaseRepository
import com.ccpp.shared.network.repository.RssFeedRepository
import com.ccpp.shared.util.ConstantsBase
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import me.toptas.rssconverter.RssConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val url = original.url.newBuilder()
                .build()
            val request = original.newBuilder()
//                .addHeader("Api-Key", BuildConfig.apiKey)
//                .addHeader("Accept", "application/json")
                .url(url).build()

            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor, interceptor: Interceptor
    ): OkHttpClient {
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(ConstantsBase.REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(ConstantsBase.REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(ConstantsBase.REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
        httpClient.addInterceptor(loggingInterceptor)
        httpClient.addInterceptor(interceptor)
        return httpClient.build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttp)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(RssConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
    }

    @Provides
    @Singleton
    fun provideLoginRepository(
        apiService: ApiService,
        baseRepository: BaseRepository
    ): RssFeedRepository = RssFeedRepository(
        apiService, baseRepository
    )

}
