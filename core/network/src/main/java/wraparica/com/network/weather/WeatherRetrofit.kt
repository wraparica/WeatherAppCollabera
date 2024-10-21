package wraparica.com.network.weather

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import wraparica.com.model.weatherModel.OpenWeatherResponse

interface WeatherRetrofit {

    @GET("data/2.5/weather")
    suspend fun getWeather(
        @QueryMap queryMap: Map<String, String>
    ): Response<OpenWeatherResponse>
}
