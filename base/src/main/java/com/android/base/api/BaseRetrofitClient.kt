package com.android.base.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by android on 2019/7/10
 */
open class BaseRetrofitClient {

    companion object {
        private const val DEFAULT_TIMEOUT = 5
        private const val SERVICE = "http://49.4.123.158:81/mqjy"
        private const val API = "$SERVICE/"
    }

    private val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor())

            return builder.build()
        }

    fun <T> createService(serviceClass: Class<T>,
                          baseUrl : String = API, okHttpClient : OkHttpClient = client): T {
        return Retrofit.Builder().client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(serviceClass)
    }
}