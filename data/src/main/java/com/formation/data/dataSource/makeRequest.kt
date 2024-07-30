package com.formation.data.dataSource

import com.formation.domain.error.ErrorType
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T: Any, V: Any> makeRequest(
    apiRequest: suspend  () -> Response<T>,
    transform: (T?) -> V?
): Result<V> {
    return try {
        val response = apiRequest()
        if(response.isSuccessful) {
            try {
                val result = transform(response.body())
                if(result != null){
                    Result.success(result)
                } else {
                    Result.failure(ErrorType.ParseError)
                }
            } catch (e: Exception) {
                Result.failure(ErrorType.ParseError)
            }
        } else {
            Result.failure(ErrorType.HttpError(response.code()))
        }
    } catch (e: SocketTimeoutException) {
        Result.failure(ErrorType.TimeOutError)
    } catch (e: UnknownHostException) {
        Result.failure(ErrorType.UnknownHostException)
    } catch (e: Exception) {
        Result.failure(ErrorType.UnknownNetworkError)
    }
}