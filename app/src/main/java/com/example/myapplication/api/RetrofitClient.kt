package com.example.myapplication.api

import com.android.base.api.BaseRetrofitClient

/**
 * Created by android on 2019/7/10
 */
object RetrofitClient : BaseRetrofitClient() {

    val service by lazy {
        createService(FactoryInters::class.java)
    }
}