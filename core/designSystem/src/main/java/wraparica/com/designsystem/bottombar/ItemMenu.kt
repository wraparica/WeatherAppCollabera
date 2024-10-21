package wraparica.com.designsystem.bottombar

import wraparica.com.designsystem.R

sealed class ItemMenu (
    val icon: Int,
    val title: String,
    val route: String
) {
    object Dashboard: ItemMenu(
        R.drawable.current_weather, "Current Weather", "dashboard_route/"
    )
    object Inventory: ItemMenu(
        R.drawable.previous_weather, "Previous Weather", "previous_route/"
    )
}