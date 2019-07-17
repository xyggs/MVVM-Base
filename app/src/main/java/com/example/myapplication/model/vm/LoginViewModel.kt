package com.example.myapplication.model.vm

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.base.base.BaseViewModel
import com.android.base.base.StateLiveData
import com.example.myapplication.model.bean.Xbean
import com.example.myapplication.model.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by android on 2019/7/10
 */
class LoginViewModel : BaseViewModel() {
    lateinit var map : HashMap<String,String>
    private val repository by lazy { LoginRepository() }
    val loginInfo : StateLiveData<Xbean> = StateLiveData()

    fun login(){
        map = HashMap()
        map["mobile"] = "13368287603"
        map["password"] = "111111"

        launch{
            val result = repository.login(map)
            loginInfo.postValueAndSuccess(result.data)
        }

    }

}