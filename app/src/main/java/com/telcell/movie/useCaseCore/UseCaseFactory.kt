package com.telcell.movie.useCaseCore

import com.connector.usecase.ThreadExecutor
import com.telcell.movie.di.module.AppModule.Companion.MAIN_TO_MAIN_NAMED
import com.telcell.movie.di.module.AppModule.Companion.OTHER_TO_MAIN_NAMED
import com.telcell.movie.main.movies.GetPopularMoviesUseCase
import com.telcell.movie.rest.ExceptionMapperImpl
import com.telcell.movie.rest.RepoApi
import dagger.Lazy
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class UseCaseFactory @Inject constructor(
    val repoApi: Lazy<RepoApi>,
    @Named(OTHER_TO_MAIN_NAMED) val otherToMaainExecutor: Lazy<ThreadExecutor>,
    @Named(MAIN_TO_MAIN_NAMED) val mainToMainExecutor: Lazy<ThreadExecutor>,
    val exceptionMapper: Lazy<ExceptionMapperImpl>

) {
    inline fun <reified T : SingleUseCase<*, *>> createSingle(): T {
        val classType = T::class.java.name
        return when (classType) {
            GetPopularMoviesUseCase::class.java.name -> GetPopularMoviesUseCase(repoApi.get()).apply { setupBackground(this) }
            else -> throw RuntimeException("$classType not implemented type")
        } as T
    }

    fun setupBackground(baseUseCase: BaseUseCase<*, *>) {
        baseUseCase.executor = otherToMaainExecutor.get()
        baseUseCase.mapper = exceptionMapper.get()
    }

    fun setupMain(baseUseCase: BaseUseCase<*, *>) {
        baseUseCase.executor = mainToMainExecutor.get()
        baseUseCase.mapper = exceptionMapper.get()
    }
}