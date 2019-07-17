package com.android.base

import android.app.Application
import android.graphics.Color
import android.text.TextUtils
import androidx.core.content.ContextCompat
import com.loper7.layout.TitleBar

/**
 * Created by android on 2019/7/5
 */
open class BaseApp : Application() {

    companion object{
        var app : BaseApp ?= null
    }

    override fun onCreate() {
        super.onCreate()
        app = this

        TitleBar.getConfig()
            .setTitleTextSize(applicationContext, 16)
            .setTitleTextColor(ContextCompat.getColor(applicationContext, R.color.colorTextBlackLight))
            .setMenuTextSize(applicationContext, 14)
            .setMenuTextColor(ContextCompat.getColor(applicationContext, R.color.colorTextBlackLight))
            .setPadding(applicationContext, 16)
            .setCenterTitle(false)
            .setUseRipple(false)
            .setTitleTextBold(true)
            .setTitleEllipsize(TextUtils.TruncateAt.MARQUEE)
            .setBackgroundColor(Color.WHITE)
            .setBorderColor(ContextCompat.getColor(applicationContext, R.color.border))
            .setShowBorder(false)
            .setBorderWidth(applicationContext, 0.6f)
//            .setActivityBackAnim(R.anim.activity_backward_enter, R.anim.activity_backward_exit);
    }

}