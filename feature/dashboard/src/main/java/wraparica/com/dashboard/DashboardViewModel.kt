package wraparica.com.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import wraparica.com.data.weather.InMemoryRepository
import wraparica.com.data.weather.WeatherRepository
import wraparica.com.data.weather.entity.PreviousWeatherEntity
import wraparica.com.data.weather.entity.toEntity
import wraparica.com.data.weather.repository.OfflinePreviousWeatherRepository
import wraparica.com.data.weather.repository.OfflineUserRepository
import wraparica.com.designsystem.convertToDate
import wraparica.com.model.weatherModel.PreviousWeatherModel
import wraparica.com.model.weatherModel.PreviousWeatherUiState
import wraparica.com.model.weatherModel.WeatherUiState
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val offlineUserRepository: OfflineUserRepository,
    private val offlinePreviousWeatherRepository: OfflinePreviousWeatherRepository,
    private val inMemoryRepository: InMemoryRepository
) : ViewModel() {

    private val _weatherResponse = MutableStateFlow(PreviousWeatherModel())
    private val _previousWeather = MutableStateFlow(emptyList<PreviousWeatherModel>())
    var weatherCalled = false
    private val _weatherCalled = MutableStateFlow(false)
    fun updateWeatherCalled(called: Boolean) {
        _weatherCalled.update { called }
    }


    val weatherUiState = combine(_weatherResponse, _weatherCalled) { weather, weatherCalled ->
        WeatherUiState(weather = weather, weatherCalled = weatherCalled)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = WeatherUiState()
    )


    val previousWeatherUiState = combine(_previousWeather, _previousWeather) { previous, _ ->
        PreviousWeatherUiState(previous)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = PreviousWeatherUiState()
    )
    fun getcalled() :Boolean{
        return inMemoryRepository.getWeatherCalled()
    }


    fun getCurrentWeather(lat: Double, long: Double) {
        viewModelScope.launch {
            val result = weatherRepository.currentWeather(lat, long).fold(
                ifLeft = { PreviousWeatherModel() },
                ifRight = {
                    val cts = System.currentTimeMillis()
                    val dateAndTime = cts.convertToDate()

                    PreviousWeatherModel(
                        id = 0,
                        sunrise = it.sys.sunrise,
                        sunset = it.sys.sunset,
                        icon = it.weather.firstOrNull()?.icon ?: "50n",
                        temp = it.main.temp.toString().substringBefore(".") + "°C",
                        weatherMain = it.weather.firstOrNull()?.main ?: "N/A",
                        userId = offlineUserRepository.getCurrentLoginUser()?.id ?: 0,
                        cts = dateAndTime,
                        country = it.sys.country,
                        name = it.name
                    )
                }
            )
            if (result.temp != "N/A") {
                offlinePreviousWeatherRepository.insert(result.toEntity())
            }
            _weatherResponse.update { result }
            inMemoryRepository.setWeatherCalled(true)
        }

    }

    fun logout(){
        viewModelScope.launch {
            offlineUserRepository.logout()
        }
    }

    fun getPreviousWeather() {
        viewModelScope.launch {
            val loggedInUser = offlineUserRepository.getCurrentLoginUser()
            if (loggedInUser != null) {
                val result = offlinePreviousWeatherRepository.getPreviousWeather(loggedInUser.id)
                _previousWeather.update { result.first() }
            }
        }
    }
}


