package wraparica.com.network.util

import retrofit2.Response
import wraparica.com.network.converter.GenericCustomAdapter
import wraparica.com.network.di.sharedMoshi

private val errorJsonJsonAdapter = GenericCustomAdapter(sharedMoshi)

internal fun <T> Response<T>.toNetworkResult(): NetworkResult<T> {
    val result = if (isSuccessful) {
        checkNotNull(body()) {
            "This should not be null"
        }.let { responseBody ->
            NetworkResult.Success<T>(responseBody)
        }
    } else {
        extractHttpError(
            rawBody = checkNotNull(errorBody()) { "This should not be null" }.string(),
            httpCode = code()
        )
    }
    return result
}

internal fun Response<Unit?>.toNetworkEmptyResult(): NetworkResult<Unit> {
    val result = if (isSuccessful) {
        check(body() == null || body() == Unit) {
            "Empty response body"
        }.let {
            NetworkResult.Success(Unit)
        }
    } else {
        extractHttpError(
            rawBody = checkNotNull(errorBody()) { "This should not be null" }.string(),
            httpCode = code()
        )
    }
    return result
}

internal fun <T> extractHttpError(
    rawBody: String,
    httpCode: Int
): NetworkResult<T> {
    val errorTokenResponse = runCatching { errorJsonJsonAdapter.fromJson(rawBody) }.getOrNull()
    return NetworkResult.Error(
        NetworkError.Http(
            httpStatusCode = httpCode,
            errorTokenResponse = errorTokenResponse
        )
    )
}

internal fun Throwable.toNetworkResult(): NetworkResult<Nothing> = when (this) {
    is NetworkError.NoInternet -> NetworkResult.Error(error = NetworkError.NoInternet)
    else -> NetworkResult.Error(error = NetworkError.IOError(this))
}