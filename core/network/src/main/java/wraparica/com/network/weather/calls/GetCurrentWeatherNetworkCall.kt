package wraparica.com.network.weather.calls

import android.util.Log
import arrow.core.Either
import arrow.core.None
import arrow.core.Option
import arrow.core.left
import arrow.core.right
import arrow.core.some
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import wraparica.com.model.weatherModel.OpenWeatherError
import wraparica.com.model.weatherModel.OpenWeatherResponse
import wraparica.com.network.BuildConfig
import wraparica.com.network.util.NetworkError
import wraparica.com.network.util.NetworkResult
import wraparica.com.network.util.toNetworkResult
import wraparica.com.network.weather.WeatherRetrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCurrentWeatherNetworkCall @Inject constructor(
    private val weatherRetrofit: WeatherRetrofit,
) {

    suspend operator fun invoke(lat: Double, long: Double): Either<OpenWeatherError, OpenWeatherResponse > =
    withContext(Dispatchers.IO)
    {
        val query = mutableMapOf<String, String>()
        query["lat"] = lat.toString()
        query["lon"] = long.toString()
        query["units"] = "metric"
        query["appid"] = BuildConfig.OPEN_WEATHER_APP_ID
        val response = kotlin.runCatching {
            weatherRetrofit.getWeather(queryMap = query)
        }.fold(
            Response<OpenWeatherResponse>::toNetworkResult,
            Throwable::toNetworkResult
        )
        when (response) {
            is NetworkResult.Error -> response.error.toOpenWeatherError().left()
            is NetworkResult.Success -> response.data.right()
        }
    }
}

private fun NetworkError.toOpenWeatherError(): OpenWeatherError {
    return when (this) {
        is NetworkError.Http, is NetworkError.IOError -> OpenWeatherError.General(
            "Error"
        )

        NetworkError.NoInternet -> OpenWeatherError.NoInternetConnection
    }
}