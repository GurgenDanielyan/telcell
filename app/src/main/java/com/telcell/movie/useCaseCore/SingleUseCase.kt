package com.telcell.movie.useCaseCore

import io.reactivex.Single

abstract class SingleUseCase<Params : Any, T> : BaseUseCase<Params, Single<T>>() {

    protected abstract fun buildUseCaseObservable(params: Params): Single<T>

    override fun execute(params: Params): Single<T> {
        return this.buildUseCaseObservable(params)
            .onErrorResumeNext { t: Throwable -> Single.error(onErrorMap(t)) }
            .observeOn(executor.schedulerUi)
            .subscribeOn(executor.schedulerIo)
    }

    protected open fun onErrorMap(throwable: Throwable): Throwable {
        return mapper.map(throwable)
    }
}

