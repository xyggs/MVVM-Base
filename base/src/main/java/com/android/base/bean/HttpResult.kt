package com.android.base.bean

/**
 * Created by android on 2019/7/10
 */
data class HttpResult<T>(val status : Int, val message : String, val data : T)