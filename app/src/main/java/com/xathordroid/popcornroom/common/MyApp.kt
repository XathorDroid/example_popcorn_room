package com.xathordroid.popcornroom.common

import android.app.Application
import android.content.Context

class MyApp: Application() {

    companion object {
        lateinit var instance: MyApp

        fun getContext(): Context {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}