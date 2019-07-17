package com.example.myapplication.ui.login

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.base.base.StateLiveData
import com.android.base.ui.BaseActivity
import com.android.base.ui.BaseVMActivity
import com.example.myapplication.R
import com.example.myapplication.model.vm.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : BaseVMActivity<LoginViewModel>() {


    override fun providerVMClass(): Class<LoginViewModel>? = LoginViewModel::class.java


    override fun getContentViewId() = R.layout.activity_main

    override fun initData() {

    }


    override fun initView() {

    }

    override fun initListener() {

        button.setOnClickListener {


            mViewModel.login()
        }
    }

    override fun startObserve() {
        mViewModel.loginInfo.observe(this, Observer {
            it.apply {
                tv.text = "请i去成功"
            }
        })


    }



}
