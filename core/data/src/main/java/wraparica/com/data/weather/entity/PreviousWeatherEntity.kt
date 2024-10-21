package wraparica.com.data.weather.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import wraparica.com.model.weatherModel.PreviousWeatherModel

@Entity(tableName = "previous_weather")
data class PreviousWeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int = 0,
    val sunrise: Long = 0L,
    val sunset: Long = 0L,
    val icon: String = "50n",
    val temp: String = "N/A",
    val weatherMain: String = "N/A",
    val cts: String = "",
    val name: String = "",
    val country: String = "",
)

fun PreviousWeatherEntity.toDomain(): PreviousWeatherModel = PreviousWeatherModel(
    id = id,
    userId = userId,
    icon = icon,
    temp = temp,
    weatherMain = weatherMain,
    sunrise = sunrise,
    sunset = sunset,
    cts = cts,
    name = name,
    country = country,
)

fun PreviousWeatherModel.toEntity(): PreviousWeatherEntity = PreviousWeatherEntity(
    id = id,
    userId = userId,
    icon = icon,
    temp = temp,
    weatherMain = weatherMain,
    sunrise = sunrise,
    sunset = sunset,
    cts = cts,
    name = name,
    country = country,
)

fun Flow<List<PreviousWeatherEntity>?>.toListDomain() = this.map { it?.map { it.toDomain()} ?: emptyList() }