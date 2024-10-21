package wraparica.com.model.weatherModel

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class OpenWeatherResponse(
   /* @Json(name = "coord")
    val coord: Coord = Coord(),*/
    @Json(name = "weather")
    val weather: List<Weather> = emptyList(),
    //val base: String = "",
    @Json(name = "main")
    val main: Main = Main(),
    //val visibility: Int = 0,
    //val wind: Wind = Wind(),
    //val rain: Rain = Rain(),
    //val clouds: Clouds = Clouds(),
   //val dt: Int = 0,
    //val timezone: Int = 0,
   // val id: Int = 0,
    val name: String = "",
   // val cod: Int = 0,
    @Json(name = "sys")
     val sys: Sys = Sys()
)  : Serializable

data class Coord(
    val lon: Double = 0.0,
    val lat: Double  = 0.0,
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String,
)

data class Main(
    @Json(name = "temp")
    val temp: Double  = 0.0,
    /*@Json(name = "feels_like")
    val feelsLike: Double  = 0.0,
    @Json(name = "temp_min")
    val tempMin: Double  = 0.0,
    @Json(name = "temp_max")
    val tempMax: Double  = 0.0,
    @Json(name = "pressure")
    val pressure: String = "",
    @Json(name = "humidity")
    val humidity: String = "",
    @Json(name = "sea_level")
    val seaLevel: String = "",
    @Json(name = "grnd_level")
    val grndLevel: String = "",*/
)

data class Wind(
    @Json(name = "speed")
    val speed: Double  = 0.0,
    @Json(name = "deg")
    val deg: Int = 0,
    @Json(name = "gust")
    val gust: Double  = 0.0,
)

data class Rain(
    @Json(name = "1h")
    val oneH: Double  = 0.0,
)

data class Clouds(
    @Json(name = "clouds")
    val clouds: Int = 0,
)

data class Sys(
    /*@Json(name = "type")
    val type: Int = 0,
    @Json(name = "type")
    val id: Int = 0,*/
    @Json(name = "country")
    val country: String = "",
    @Json(name = "sunrise")
    val sunrise: Long = 0,
    @Json(name = "sunset")
    val sunset: Long = 0,
)

sealed class OpenWeatherError {

    object NoInternetConnection : OpenWeatherError()

    data class General(val reason: String) : OpenWeatherError()
}