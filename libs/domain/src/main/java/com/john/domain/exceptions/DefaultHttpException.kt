package com.john.domain.exceptions

data class DefaultHttpException(
    override val message: String
) : Exception()