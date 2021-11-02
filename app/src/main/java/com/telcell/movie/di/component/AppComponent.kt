package com.telcell.movie.di.component

import com.telcell.movie.di.module.AppModule
import com.telcell.movie.di.module.RestModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RestModule::class])
interface AppComponent {

    //    val dBInteractor: DBInteractor
//
    fun addChildMainComponent(): MainComponent

    //
    fun addChildAuthComponent(): AuthComponent
//
//    fun getUserServiceMaster(): UserServiceMaster
//
//    fun getAppSettings(): AppSettings
//
//    fun getLocaleHelper(): LocaleHelper
//
//    fun getRepoApi(): RepoApi
//
//    fun getUseCaseFactory(): UseCaseFactory
//
//    @Named(AppModule.OTHER_TO_MAIN_NAMED)
//    fun getThreadExecutor(): ThreadExecutor
//
//    fun getExceptionMapper(): ExceptionMapper
}