package wraparica.com.dashboard.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.LatLng
import wraparica.com.dashboard.DashboardViewModel
import wraparica.com.designsystem.bottombar.BottomAppBarComponent
import wraparica.com.designsystem.weatherDetails.WeatherDetailComponent
import wraparica.com.model.weatherModel.OpenWeatherResponse
import wraparica.com.model.weatherModel.WeatherUiState

@Composable
internal fun DashboardRoute(
    navController: NavHostController,
    onBottomNavClick: (route: String) -> Unit,
    viewModel: DashboardViewModel = hiltViewModel(),
    currentLocation: LatLng,
    navigateToLoginRoute: () -> Unit
) {
    val uiState by viewModel.weatherUiState.collectAsStateWithLifecycle()
    /*if (!uiState.weatherCalled) {
        viewModel.getCurrentWeather()
        viewModel.updateWeatherCalled(true)
    }*/

    viewModel.getCurrentWeather(lat = currentLocation.latitude, long = currentLocation.longitude)
    DashboardScreen(
        navController = navController,
        onBottomNavClick = onBottomNavClick,
        uiState = { uiState },
        onLogoutClick = {
            viewModel.logout()
            navigateToLoginRoute()
        }
    )
}

@Composable
internal fun DashboardScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onBottomNavClick: (route: String) -> Unit,
    onLogoutClick: () -> Unit,
    uiState: () -> WeatherUiState,
) {
    Scaffold(modifier = modifier,
        bottomBar = {
            BottomAppBarComponent(
                navController = navController,
                onBottomNavClick = onBottomNavClick
            )
        }) {
        Surface {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(color = Color(0xFF60D5D3))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    WeatherDetailComponent(uiState = uiState().weather)
                }
                Box() {

                    TextButton(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        onClick = { onLogoutClick() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue,
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Log out",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.White,
                            fontStyle = FontStyle.Italic,
                        )
                    }
                }


            }
        }
    }
}