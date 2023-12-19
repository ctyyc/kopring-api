package com.kopring.api.common.exception

const val INVALID_ARGUMENT: String = "Invalid Argument"

data class ErrorResponse(
        val message: String,
        val errorType: String = INVALID_ARGUMENT
)