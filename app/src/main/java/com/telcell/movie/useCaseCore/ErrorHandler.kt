package com.connector.usecase

interface ErrorHandler {
    fun handleError(throwable: Throwable, requestCode: Int)
}