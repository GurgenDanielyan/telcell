package com.telcell.movie.di.module

import com.telcell.movie.BuildConfig
import com.telcell.movie.rest.API
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RestModule {

    companion object {
        const val LIVE = "https://api.themoviedb.org"

        fun getBaseUrl(): String {
            return LIVE
        }
    }

    @Provides
    @Singleton
    fun provideApi(converter: Converter.Factory): API {
        val client = getOkHttpClient()
        val retrofit = Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(converter)
            .client(client)
            .build()
        return retrofit.create(API::class.java)
    }

    fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    @Provides
    fun provideConverter(): Converter.Factory {
        return GsonConverterFactory.create()
    }


}