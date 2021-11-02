package com.telcell.movie.useCaseCore

import com.connector.usecase.ExceptionMapper
import com.connector.usecase.ThreadExecutor

abstract class BaseUseCase<Params, T> {

    lateinit var executor: ThreadExecutor
    lateinit var mapper: ExceptionMapper

    @Suppress("UNCHECKED_CAST")
    abstract fun execute(params: Params = Any() as Params): T
}

