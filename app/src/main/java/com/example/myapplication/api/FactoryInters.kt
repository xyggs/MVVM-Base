package com.example.myapplication.api

import com.android.base.bean.HttpResult
import com.example.myapplication.model.bean.Xbean
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by android on 2019/7/10
 */
interface FactoryInters {
    @FormUrlEncoded
    @POST("public/member/app/account/login")
    suspend fun login(@FieldMap map : Map<String,String>) : HttpResult<Xbean>
}