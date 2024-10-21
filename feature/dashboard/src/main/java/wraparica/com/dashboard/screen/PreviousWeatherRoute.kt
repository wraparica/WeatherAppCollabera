package wraparica.com.dashboard.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import wraparica.com.dashboard.DashboardViewModel
import wraparica.com.designsystem.bottombar.BottomAppBarComponent
import wraparica.com.designsystem.weatherDetails.WeatherDetailComponent
import wraparica.com.model.weatherModel.PreviousWeatherUiState
import wraparica.com.model.weatherModel.WeatherUiState

@Composable
internal fun PreviousRoute(
    navController: NavHostController,
    onBottomNavClick: (route: String) -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val uiState by viewModel.previousWeatherUiState.collectAsStateWithLifecycle()
    viewModel.getPreviousWeather()
    PreviousWeatherScreen(
        navController = navController,
        onBottomNavClick = onBottomNavClick,
        uiState = { uiState }
    )
}

@Composable
internal fun PreviousWeatherScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onBottomNavClick: (route: String) -> Unit,
    uiState: () -> PreviousWeatherUiState,
) {
    Scaffold(modifier = modifier,
        bottomBar = {
            BottomAppBarComponent(
                navController = navController,
                onBottomNavClick = onBottomNavClick
            )
        }) {
        Surface {
            Column (
                modifier = Modifier
                    .padding(it)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(color = Color(0xFF60D5D3))
            )  {

                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(0.dp),
                    rememberLazyListState(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    itemsIndexed(uiState().previousWeather) { _, item ->
                        WeatherDetailComponent(uiState = item)
                    }
                }
            }
        }
    }
}