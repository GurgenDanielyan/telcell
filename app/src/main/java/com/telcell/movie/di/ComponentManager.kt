package com.telcell.movie.di

import android.app.Application
import com.telcell.movie.di.component.AppComponent
import com.telcell.movie.di.component.AuthComponent
import com.telcell.movie.di.component.DaggerAppComponent
import com.telcell.movie.di.component.MainComponent

class ComponentManager private constructor() {

    private lateinit var appComponent: AppComponent
    private var mainComponent: MainComponent? = null
    private var authComponent: AuthComponent? = null

    companion object {
        @Volatile
        private var INSTANCE: ComponentManager? = null

        fun getInstance(): ComponentManager = INSTANCE ?: synchronized(this) {
            INSTANCE ?: ComponentManager()
                .also { INSTANCE = it }
        }
    }

    fun initAppComponent(application: Application) {
        appComponent = DaggerAppComponent.builder()
//                .appModule(AppModule(application))
//                .networkUtils(NetworkUtils())
            .build()
    }

    fun getAppComponent() = appComponent

    fun getAuthComponent() = authComponent ?: appComponent
        .addChildAuthComponent()
        .also { authComponent = it }

    fun clearAuthComponent() {
        authComponent = null
    }

    fun getMainComponent() = mainComponent ?: appComponent
        .addChildMainComponent()
        .also { mainComponent = it }

    fun clearMainComponent() {
        mainComponent = null
    }

    fun clearAllComponents() {
        clearAuthComponent()
        clearMainComponent()
    }
}