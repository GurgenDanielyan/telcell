package com.telcell.movie

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.telcell.movie.di.ComponentManager

class App : Application() {
    companion object {

        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        lateinit var sContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        sContext = this
        initAppComponent()
    }

    private fun initAppComponent() {
        ComponentManager.getInstance().initAppComponent(this)
    }
}