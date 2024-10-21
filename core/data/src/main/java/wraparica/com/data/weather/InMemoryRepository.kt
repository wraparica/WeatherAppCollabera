package wraparica.com.data.weather

import android.util.Log
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class InMemoryRepository @Inject constructor() : InMemory {
    private var weatherCalled: Boolean = false

    override fun getWeatherCalled(): Boolean {
        return weatherCalled
    }
    override fun setWeatherCalled(weatherCalled: Boolean) {

        this.weatherCalled = weatherCalled
    }
}

interface InMemory {
    fun getWeatherCalled(): Boolean
    fun setWeatherCalled(weatherCalled: Boolean)
}