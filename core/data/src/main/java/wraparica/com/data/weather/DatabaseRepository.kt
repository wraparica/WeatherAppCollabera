package wraparica.com.data.weather

import arrow.core.Either
import wraparica.com.data.weather.dao.UserDao
import wraparica.com.data.weather.entity.UserEntity
import wraparica.com.data.weather.repository.OfflineUserRepository
import wraparica.com.model.weatherModel.OpenWeatherError
import wraparica.com.model.weatherModel.OpenWeatherResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DatabaseRepository @Inject constructor(
    private val offlineUserRepository: OfflineUserRepository
) {
    suspend fun insertUser(userEntity: UserEntity): Long {
        return offlineUserRepository.insert(userEntity)
    }
}