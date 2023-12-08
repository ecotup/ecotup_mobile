package com.ecotup.ecotupapplication.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.navigation.NavItemDriver
import com.ecotup.ecotupapplication.ui.navigation.Screen

@Composable
fun BottomNavigationDriver(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navStackBackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navStackBackEntry?.destination?.route

        val navItem = listOf(
            NavItemDriver(
                title = "Home",
                icon = painterResource(id = R.drawable.home),
                screen = Screen.HomeScreenDriver,
                contentDescription = "home_driver"
            ),

            NavItemDriver(
                title = "Income",
                icon = painterResource(id = R.drawable.income),
                screen = Screen.IncomeScreenDriver,
                contentDescription = "income_driver"
            ),

            NavItemDriver(
                title = "History",
                icon = painterResource(id = R.drawable.history),
                screen = Screen.HistoryScreenDriver,
                contentDescription = "history_driver"
            ),

            NavItemDriver(
                title = "Setting",
                icon = painterResource(id = R.drawable.setting),
                screen = Screen.SettingScreenDriver,
                contentDescription = "setting_driver"
            ),
        )

        navItem.map { itemDriver ->
            NavigationBarItem(
                selected = currentRoute == itemDriver.screen.route,
                onClick = {
                    navController.navigate(itemDriver.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        painter = itemDriver.icon, contentDescription = itemDriver.title
                    )
                },
                label = {
                    Text(
                        text = itemDriver.title,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp)
                    )
                }
            )
        }
    }
}