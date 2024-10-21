package wraparica.com.user

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import wraparica.com.data.weather.entity.UserEntity
import wraparica.com.data.weather.repository.OfflineUserRepository
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val offlineUserRepository: OfflineUserRepository
) : ViewModel(){

    private val _username = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _name = MutableStateFlow("")
    private val _proceed = MutableStateFlow(false)
    private val _proceedToDashboard = MutableStateFlow(false)

    fun updateUsername(name: String) {
        _username.update { name }
    }
    fun updatePassword(password: String) {
        _password.update { password }
    }
    fun updateName(name: String) {
        _name.update { name }
    }

    val signupUiState = combine(_name,_username, _password, _proceed) { name, username, password, proceed ->
        LoginUiState(
            name = name,
            username = username,
            password = password,
            proceed = proceed
        )

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = LoginUiState()
    )

    val loginState = combine(_username, _password, _proceed, _proceedToDashboard) { username, password, proceed, proceedToDashboard ->
        LoginUiState(
            username = username,
            password = password,
            proceed = proceed,
            proceedToDashboard = proceedToDashboard
        )

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = LoginUiState()
    )

    fun login() {
        viewModelScope.launch {
            val user = offlineUserRepository.checkUser(_username.value, _password.value)
            if (user == null) {
                _proceed.update { false }
            } else {
                offlineUserRepository.update(user.copy(login = true))
                _proceed.update { true }
            }

            /*if (user.id) {

            }*/
        }
    }

    init {
        viewModelScope.launch {
            val loggedInUser = offlineUserRepository.getCurrentLoginUser()
            if (loggedInUser != null) {
                _proceedToDashboard.update { true }
            }
        }
    }

    fun signup() {
        viewModelScope.launch {
            offlineUserRepository.insert(UserEntity(
                name = _name.value,
                username = _username.value,
                password = _password.value
            ))
        }
    }
}

data class LoginUiState(
    val name: String = "",
    val username: String = "",
    val password: String = "",
    val proceed: Boolean = false,
    val proceedToDashboard: Boolean = false,
)
