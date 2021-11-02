package com.connector.usecase

interface ExceptionMapper {
    fun map(throwable: Throwable): Throwable
}