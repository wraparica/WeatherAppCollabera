package wraparica.com.user

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import wraparica.com.user.screen.UserLoginRoute

const val userLoginNavigationRoute = "login_route/"
fun NavController.navigateToUserLoginRoute() {
    this.navigate(userLoginNavigationRoute){
        launchSingleTop = true
        popUpTo(0)
    }
}

fun NavGraphBuilder.userLoginGraph(
    navController: NavHostController,
    navigateToDashboardRoute: () -> Unit,
    navigateToSignupRoute: () -> Unit
) {
    composable(
        route = userLoginNavigationRoute
    ) {
        UserLoginRoute(navigateToDashboardRoute = navigateToDashboardRoute, navigateToSignupRoute = navigateToSignupRoute)
    }
}
