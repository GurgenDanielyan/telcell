package com.telcell.movie.useCaseCore

import com.connector.usecase.ThreadExecutor
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ThreadExecutorOtherToMainImpl : ThreadExecutor {
    override val schedulerIo: Scheduler
        get() = Schedulers.io()
    override val schedulerUi: Scheduler
        get() = AndroidSchedulers.mainThread()
}

class ThreadExecutorMainToMainImpl : ThreadExecutor {
    override val schedulerIo: Scheduler
        get() = AndroidSchedulers.mainThread()
    override val schedulerUi: Scheduler
        get() = AndroidSchedulers.mainThread()

}