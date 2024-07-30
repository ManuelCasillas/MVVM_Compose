package com.formation.domain.error

sealed class ErrorType: Throwable() {
    data class HttpError(val code: Int): ErrorType()
    data class UnknownError(val error: String): ErrorType()
    object ParseError: ErrorType()
    object TimeOutError: ErrorType()
    object UnknownHostException: ErrorType()
    object UnknownNetworkError: ErrorType()
}

fun ErrorType.getString(): String {
    return when(this){
        is ErrorType.HttpError -> "Error ${this.code}"
        ErrorType.ParseError -> "Error parseando los datos"
        ErrorType.TimeOutError -> "Time out"
        is ErrorType.UnknownError -> "Error desconocido"
        ErrorType.UnknownHostException -> "Host desconocido"
        ErrorType.UnknownNetworkError -> "Error de red desconocido"
    }
}
