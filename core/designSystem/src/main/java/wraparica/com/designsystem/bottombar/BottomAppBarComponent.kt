package wraparica.com.designsystem.bottombar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomAppBarComponent(
    navController: NavHostController,
    onBottomNavClick: (String) -> Unit
) {
    val navigationItem = listOf(
        ItemMenu.Dashboard,
        ItemMenu.Inventory
    )
    BottomNavigation(
        backgroundColor = Color.White,
    ) {
        val currentRoute = currentRoute(navController = navController)
        navigationItem.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = { onBottomNavClick(item.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                label = { Text(item.title) },
                alwaysShowLabel = false,
            )
        }
    }


}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val entry by navController.currentBackStackEntryAsState()
    return entry?.destination?.route
}