package com.telcell.movie.di.component

import com.telcell.movie.di.module.AuthModule
import com.telcell.movie.di.scope.AuthScope
import com.telcell.movie.splash.SplashActivity
import dagger.Subcomponent

@Subcomponent(modules = [AuthModule::class])
@AuthScope
interface AuthComponent {

    fun inject(activity: SplashActivity)
}