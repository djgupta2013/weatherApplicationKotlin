package com.wildnet.weatherapplicationkotlin.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object {

        var BaseUrl = ""

        private var retrofit: Retrofit? = null
        internal var httpClient = OkHttpClient.Builder()
                //here we can add Interceptor for dynamical adding headers
                .addInterceptor { chain ->
                    val request: Request
                    request = chain.request().newBuilder().addHeader("test", "test").build()
                    chain.proceed(request)
                }
                //here we adding Interceptor for full level logging
                .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .readTimeout(100, TimeUnit.SECONDS)
                .connectTimeout(100, TimeUnit.SECONDS)
                .build()

        fun getClient(baseUrl: String): AppRequestService {

            if (retrofit == null || BaseUrl != baseUrl) {
                retrofit = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(httpClient)
                        .build()
            }
            BaseUrl = baseUrl
            return retrofit!!.create(AppRequestService::class.java!!)
        }
    }
}