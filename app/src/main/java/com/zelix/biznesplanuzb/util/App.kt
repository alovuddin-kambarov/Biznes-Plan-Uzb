package com.zelix.biznesplanuzb.util

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MySharedPreference.getInstance(this)

    }

}