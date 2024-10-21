package wraparica.com.dashboard

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.google.android.gms.maps.model.LatLng
import wraparica.com.dashboard.screen.DashboardRoute

const val dashboardNavigationRoute = "dashboard_route/"
fun NavController.navigateToDashboardRoute() {
    this.navigate(dashboardNavigationRoute){
        launchSingleTop = true
        popUpTo(0)
    }
}

fun NavGraphBuilder.weatherGraph(
    navController: NavHostController,
    nestedGraphs: NavGraphBuilder.() -> Unit,
    navigateToPreviousWeatherUpdates: () -> Unit,
    navigateToLoginRoute: () -> Unit,
    currentLocation: LatLng
) {
    composable(
        route = dashboardNavigationRoute
    ) {
        DashboardRoute(
            navController = navController,
            currentLocation = currentLocation,
            navigateToLoginRoute = navigateToLoginRoute,
            onBottomNavClick = {
                when (it) {
                    "previous_route/" -> {
                        navigateToPreviousWeatherUpdates()
                    }
                    else -> {

                    }
                }
            }
        )
    }
    nestedGraphs()
}