package wraparica.com.network.di

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import wraparica.com.model.weatherModel.Weather
import wraparica.com.network.NetworkConfig
import wraparica.com.network.interceptor.ConnectivityInterceptor
import wraparica.com.network.util.DefaultNetworkStatusProvider
import wraparica.com.network.util.NetworkStatusProvider
import wraparica.com.network.weather.WeatherRetrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

internal val sharedMoshi = Moshi.Builder().build()

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkStatusProvider(@ApplicationContext context: Context): NetworkStatusProvider {
        return DefaultNetworkStatusProvider(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        connectivityInterceptor: ConnectivityInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(connectivityInterceptor)
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkConfig.OPEN_WEATHER_DOMAIN)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherRetrofit(retrofit: Retrofit): WeatherRetrofit {
        return retrofit.create(WeatherRetrofit::class.java)
    }
}
