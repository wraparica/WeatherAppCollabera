package wraparica.com.network.util

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorInfo(
    val code: String,
    val message: String,
    val details: String,
    val displayMessage: String
)

@JsonClass(generateAdapter = true)
data class ErrorTokenResponse(
    val error: ErrorInfo?,
    val moreInfo: String?,
    val message: String?
)

sealed class NetworkError : Throwable() {

    data class Http(
        val httpStatusCode: Int,
        //override val message: String?,
        val errorTokenResponse: ErrorTokenResponse?
    ) : NetworkError()

    data class IOError(
        override val cause: Throwable?
    ) : NetworkError()

    object NoInternet : NetworkError()
}