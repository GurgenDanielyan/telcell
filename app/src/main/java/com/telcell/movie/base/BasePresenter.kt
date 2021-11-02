package com.telcell.movie.base

import com.connector.usecase.ErrorHandler
import com.connector.usecase.RXProgress
import com.connector.usecase.RxExecutor
import com.telcell.movie.rest.NoNetworkException
import moxy.MvpPresenter

open class BasePresenter<View : BaseView> : MvpPresenter<View>(), RXProgress, ErrorHandler {
    protected val rxExecutor = RxExecutor()

    init {
        rxExecutor.errorHandler = this
        rxExecutor.progress = this
    }

    override fun showProgress() {
        viewState.showProgress(true)
    }

    override fun hideProgress() {
        viewState.showProgress(false)
    }

    override fun handleError(throwable: Throwable, requestCode: Int) {
        when (throwable) {
            is NoNetworkException -> {
                viewState.showNetworkError()
            }
            else -> {
                viewState.showServerError()
            }
        }
    }
}