package com.android.base.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Created by android on 2019/7/10
 */
open class BaseViewModel : ViewModel(), LifecycleObserver {

    //var baseLoading : MutableLiveData<String> = MutableLiveData()

    fun launch(block: suspend CoroutineScope.() -> Unit) {



        viewModelScope.launch {
            try {
                block()
            }catch (e: Exception){

            }
        }

    }
}