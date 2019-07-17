package com.android.base.base

import androidx.lifecycle.MutableLiveData

/**
 * Created by android on 2019/7/16
 */
class StateLiveData<T> : MutableLiveData<T>() {

    enum class State {
        Normal, Loading, Success,Error
    }

    val state = MutableLiveData<State>()

    init {
        clearState()
    }

    fun clearState() {
        state.postValue(State.Normal)
    }

    fun postLoading() {
        state.postValue(State.Loading)
    }

    fun hideLoading(){
        state.postValue(State.Normal)
    }

    fun postSuccess() {
        state.postValue(State.Success)
        hideLoading()
    }

    fun postError() {
        state.postValue(State.Error)
        hideLoading()
    }

    fun postValueAndSuccess(value: T) {
        super.postValue(value)
        postSuccess()
    }

    fun changeState(s: State) {
        state.postValue(s)
    }

}