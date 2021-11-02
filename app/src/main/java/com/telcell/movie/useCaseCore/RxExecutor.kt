package com.connector.usecase

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class RxExecutor {

    var errorHandler: ErrorHandler? = null
    var progress: RXProgress? = null
    private val compositeDisposable = CompositeDisposable()

    fun <T> run(single: Single<T>, handleProgress: Boolean = true, requestCode: Int = 0, error: (t: Throwable) -> Unit = {}, success: (T) -> Unit = {}): Disposable {
        if (handleProgress) progress?.showProgress()
        val disposable = single
            .doAfterTerminate {
                if (handleProgress) progress?.hideProgress()
            }
            .doOnDispose {
                if (handleProgress) progress?.hideProgress()
            }
            .subscribe({
                success(it)
            }, {
                errorHandler?.handleError(it, requestCode)
                error(it)
            })

        compositeDisposable.add(disposable)
        return disposable
    }

    fun clear() {
        compositeDisposable.clear()
    }
}