package wraparica.com.data.weather


import arrow.core.Either
import arrow.core.Option
import wraparica.com.model.weatherModel.OpenWeatherError
import wraparica.com.model.weatherModel.OpenWeatherResponse
import wraparica.com.network.weather.calls.GetCurrentWeatherNetworkCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val getCurrentWeatherNetworkCall: GetCurrentWeatherNetworkCall
){
    suspend fun currentWeather(lat: Double, long: Double): Either<OpenWeatherError,OpenWeatherResponse> {
        return getCurrentWeatherNetworkCall.invoke(lat, long)
    }
}
