package wraparica.com.data.weather

import kotlinx.coroutines.flow.Flow
import wraparica.com.data.weather.entity.PreviousWeatherEntity
import wraparica.com.model.weatherModel.PreviousWeatherModel

interface PreviousWeatherRepository {
    suspend fun insert(previousWeatherEntity: PreviousWeatherEntity): Long
    fun getPreviousWeather(userId: Int): Flow<List<PreviousWeatherModel>>
}