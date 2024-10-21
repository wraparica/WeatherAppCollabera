package wraparica.com.data.weather

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import wraparica.com.data.weather.dao.PreviousWeatherDao
import wraparica.com.data.weather.dao.UserDao
import wraparica.com.data.weather.entity.PreviousWeatherEntity
import wraparica.com.data.weather.entity.UserEntity

@Database(
    entities = [UserEntity::class, PreviousWeatherEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherAppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun previousWeatherDao(): PreviousWeatherDao

    companion object {
        const val NAME = "weather_app_database.db"
       /* @Volatile
        private var Instance: WeatherAppDatabase? = null*/
       /* fun getDatabase(context: Context): WeatherAppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, WeatherAppDatabase::class.java, "weather_app_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }*/
    }
}

