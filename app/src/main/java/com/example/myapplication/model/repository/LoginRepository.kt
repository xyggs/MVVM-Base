package com.example.myapplication.model.repository

import com.android.base.bean.HttpResult
import com.example.myapplication.api.RetrofitClient
import com.example.myapplication.model.bean.Xbean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.HashMap

/**
 * Created by android on 2019/7/10
 */
class LoginRepository {


    suspend fun login(map : HashMap<String,String>) : HttpResult<Xbean>{
        return apiCall{ RetrofitClient.service.login(map)}
    }

    private suspend fun <T> apiCall(call : suspend () -> HttpResult<T>) : HttpResult<T>{
        return withContext(Dispatchers.IO){ call.invoke()}
    }
}