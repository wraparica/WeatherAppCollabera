package wraparica.com.dashboard

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import wraparica.com.dashboard.screen.PreviousRoute

const val previousNavigationRoute = "previous_route/"
fun NavController.navigateToPreviousRoute() {
    this.navigate(previousNavigationRoute){
        launchSingleTop = true
        popUpTo(0)
    }
}

fun NavGraphBuilder.previousGraph(
    navController: NavHostController,
    navigateToDashboardRoute: () -> Unit
) {
    composable(
        route = previousNavigationRoute
    ) {
        PreviousRoute(
            navController = navController,
            onBottomNavClick = {
                when (it) {
                    "dashboard_route/" -> {
                        navigateToDashboardRoute()
                    }
                    else -> {

                    }
                }
            }
        )
    }
}