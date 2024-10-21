package wraparica.com.data.weather.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import wraparica.com.data.weather.InMemory
import wraparica.com.data.weather.InMemoryRepository
import wraparica.com.data.weather.UserRepository
import wraparica.com.data.weather.WeatherAppDatabase
import wraparica.com.data.weather.dao.PreviousWeatherDao
import wraparica.com.data.weather.dao.UserDao
import wraparica.com.network.weather.WeatherRetrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WeatherAppDatabase =
        Room.databaseBuilder(context, WeatherAppDatabase::class.java, WeatherAppDatabase.NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideUserDao(weatherAppDatabase: WeatherAppDatabase): UserDao =
        weatherAppDatabase.userDao()

    @Provides
    @Singleton
    fun provideWeatherDao(weatherAppDatabase: WeatherAppDatabase): PreviousWeatherDao =
        weatherAppDatabase.previousWeatherDao()

}

@Module
@InstallIn(SingletonComponent::class)
interface InMemoryModule {
    @Binds
    @Singleton
    fun bindInMemory(inMemoryRepository: InMemoryRepository): InMemory
}

