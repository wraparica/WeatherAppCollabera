package wraparica.com.data.weather.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import wraparica.com.data.weather.entity.PreviousWeatherEntity
import wraparica.com.data.weather.entity.UserEntity


@Dao
interface PreviousWeatherDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(previousWeatherEntity: PreviousWeatherEntity): Long

    @Query("SELECT * FROM previous_weather where userId = :userId")
    fun getPreviousWeather(userId: Int): Flow<List<PreviousWeatherEntity>>
}