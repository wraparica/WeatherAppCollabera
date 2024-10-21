package wraparica.com.network.util

sealed interface NetworkResult<out T> {

    data class Success<T>(val data: T) : NetworkResult<T>

    data class Error(val error: NetworkError) : NetworkResult<Nothing>
}