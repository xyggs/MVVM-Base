package com.android.base.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.android.base.base.BaseViewModel
import com.android.base.base.StateLiveData

/**
 * Created by android on 2019/7/15
 */
abstract class BaseVMActivity<VM : BaseViewModel> : BaseActivity(), LifecycleObserver {

    lateinit var mViewModel: VM
   // lateinit var baseViewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        initVM()
        super.onCreate(savedInstanceState)
        startObserve()


    }

    private fun initVM(){

        providerVMClass()?.let {
            mViewModel = ViewModelProviders.of(this).get(it)
            mViewModel.apply {
                lifecycle.addObserver(this)
            }
        }



    }

    abstract fun providerVMClass(): Class<VM>?

    abstract fun startObserve()

    override fun onDestroy() {
        mViewModel.let {
            lifecycle.removeObserver(it)
        }

        super.onDestroy()
    }


}


