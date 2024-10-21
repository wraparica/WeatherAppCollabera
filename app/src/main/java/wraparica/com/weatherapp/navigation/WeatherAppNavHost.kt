package wraparica.com.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.google.android.gms.maps.model.LatLng
import wraparica.com.dashboard.dashboardNavigationRoute
import wraparica.com.dashboard.navigateToDashboardRoute
import wraparica.com.dashboard.navigateToPreviousRoute
import wraparica.com.dashboard.previousGraph
import wraparica.com.dashboard.weatherGraph
import wraparica.com.user.navigateToUserLoginRoute
import wraparica.com.user.navigateToUserSignupRoute
import wraparica.com.user.userLoginGraph
import wraparica.com.user.userLoginNavigationRoute
import wraparica.com.user.userSignupGraph

@Composable
fun WeatherAppNavHost(
    modifier: Modifier,
    navHostController: NavHostController,
    startDestination: String = userLoginNavigationRoute,
    currentLocation: LatLng
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier,
    ) {

        weatherGraph(
            navController = navHostController,
            currentLocation = currentLocation,
            nestedGraphs = {
                previousGraph(
                    navController = navHostController,
                    navigateToDashboardRoute = { navHostController.navigateToDashboardRoute() }
                )
                userLoginGraph(
                    navController = navHostController,
                    navigateToDashboardRoute = { navHostController.navigateToDashboardRoute() },
                    navigateToSignupRoute = { navHostController.navigateToUserSignupRoute() }
                )
                userSignupGraph(
                    navigateToLogin = { navHostController.navigateToUserLoginRoute() }
                )
            },
            navigateToPreviousWeatherUpdates = { navHostController.navigateToPreviousRoute() },
            navigateToLoginRoute = { navHostController.navigateToUserLoginRoute() }
        )

    }
}