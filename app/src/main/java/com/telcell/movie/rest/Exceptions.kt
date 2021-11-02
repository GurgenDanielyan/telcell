package com.telcell.movie.rest

open class InternalException(message: String?) : RuntimeException(message)

class NoNetworkException(message: String? = null) : InternalException(message)