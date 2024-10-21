package wraparica.com.user

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import wraparica.com.user.screen.UserSignupRoute

const val userSignupNavigationRoute = "signup_route/"
fun NavController.navigateToUserSignupRoute() {
    this.navigate(userSignupNavigationRoute){
        launchSingleTop = true
    }
}

fun NavGraphBuilder.userSignupGraph(
    navigateToLogin: () -> Unit,
) {
    composable(
        route = userSignupNavigationRoute
    ) {
        UserSignupRoute(navigateToLoginRoute = navigateToLogin)
    }
}
