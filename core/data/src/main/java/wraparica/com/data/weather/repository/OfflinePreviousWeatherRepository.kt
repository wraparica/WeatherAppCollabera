package wraparica.com.data.weather.repository

import kotlinx.coroutines.flow.Flow
import wraparica.com.data.weather.PreviousWeatherRepository
import wraparica.com.data.weather.dao.PreviousWeatherDao
import wraparica.com.data.weather.entity.PreviousWeatherEntity
import wraparica.com.data.weather.entity.toListDomain
import wraparica.com.model.weatherModel.PreviousWeatherModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflinePreviousWeatherRepository @Inject constructor(
    private val previousWeatherDao: PreviousWeatherDao
): PreviousWeatherRepository {
    override suspend fun insert(previousWeatherEntity: PreviousWeatherEntity): Long = previousWeatherDao.insert(previousWeatherEntity)
    override fun getPreviousWeather(userId: Int): Flow<List<PreviousWeatherModel>> = previousWeatherDao.getPreviousWeather(userId).toListDomain()
}