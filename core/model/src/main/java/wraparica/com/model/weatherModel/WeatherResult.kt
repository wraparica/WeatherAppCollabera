package wraparica.com.model.weatherModel

sealed class WeatherResult<out T> {

    object Loading : WeatherResult<Nothing>()

    object Error : WeatherResult<Nothing>()

    data class Success<T>(val data: T) : WeatherResult<T>()
}

data class WeatherUiState(
    val weather: PreviousWeatherModel = PreviousWeatherModel(),
    val weatherCalled: Boolean = false
)

data class PreviousWeatherUiState(
    val previousWeather: List<PreviousWeatherModel> = emptyList(),
)
data class PreviousWeatherModel(
    val id: Int = 0,
    val userId: Int = 0,
    val sunrise: Long = 0L,
    val sunset: Long = 0L,
    val icon: String = "50n",
    val temp: String = "N/A",
    val weatherMain: String = "N/A",
    val cts: String = "",
    val name: String = "",
    val country: String = ""
)