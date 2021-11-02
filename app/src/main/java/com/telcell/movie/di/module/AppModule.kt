package com.telcell.movie.di.module

import com.connector.usecase.ThreadExecutor
import com.telcell.movie.useCaseCore.ThreadExecutorMainToMainImpl
import com.telcell.movie.useCaseCore.ThreadExecutorOtherToMainImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AppModule() {
    companion object {
        const val OTHER_TO_MAIN_NAMED = "otherToMain"
        const val MAIN_TO_MAIN_NAMED = "mainToMain"
    }

    //    @Provides
//    @Singleton
//    fun provideAppContext(): Context {
//        return mContext
//    }
//
//    @Provides
//    @Singleton
//    fun provideUserAccount(appSettings: AppSettings): LocaleHelper {
//        return LocaleHelper(appSettings)
//    }
//
    @Provides
    @Named(OTHER_TO_MAIN_NAMED)
    fun provideOtherToMainThreadExecutor(): ThreadExecutor {
        return ThreadExecutorOtherToMainImpl()
    }

    @Provides
    @Named(MAIN_TO_MAIN_NAMED)
    fun provideMainToMainThreadExecutor(): ThreadExecutor {
        return ThreadExecutorMainToMainImpl()
    }
//
//    @Provides
//    fun provideExceptionMapper(): ExceptionMapper {
//        return ExceptionMapperImpl()
//    }
}