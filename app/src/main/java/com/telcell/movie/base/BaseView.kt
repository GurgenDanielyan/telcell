package com.telcell.movie.base

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface BaseView : ConnectionErrors, Progress, MvpView

interface ConnectionErrors {
    @OneExecution
    fun showNetworkError(requestCode: Int = -1)

    @OneExecution
    fun showFatalServerError(requestCode: Int = -1)

    @OneExecution
    fun showServerError(requestCode: Int = -1)
}

interface Progress {
    @AddToEndSingle
    fun showProgress(state: Boolean)
}