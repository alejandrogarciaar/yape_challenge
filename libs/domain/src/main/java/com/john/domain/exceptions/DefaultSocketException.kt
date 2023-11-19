package com.john.domain.exceptions

data class DefaultSocketException(
    override val message: String
) : Exception()