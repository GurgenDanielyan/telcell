package com.telcell.movie.rest

import com.connector.usecase.ExceptionMapper
import com.telcell.movie.App
import com.telcell.movie.misc.NetworkUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExceptionMapperImpl @Inject constructor() : ExceptionMapper {
    override fun map(throwable: Throwable): Throwable {
        return when {
            !NetworkUtils.isConnected(App.sContext) -> NoNetworkException()
            else -> throwable
        }
    }
}