package com.connector.usecase

import io.reactivex.Scheduler

interface ThreadExecutor {
    val schedulerIo: Scheduler
    val schedulerUi: Scheduler
}