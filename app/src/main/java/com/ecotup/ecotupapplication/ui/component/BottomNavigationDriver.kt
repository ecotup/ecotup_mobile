package com.ecotup.ecotupapplication.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.navigation.NavItemDriver
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight

@Composable
fun BottomNavigationDriver(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavigationBar(
        modifier = modifier
            .height(65.dp)
            .padding(0.dp)
    ) {
        val navStackBackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navStackBackEntry?.destination?.route

        val navItem = listOf(
            NavItemDriver(
                title = stringResource(R.string.home),
                icon = painterResource(id = R.drawable.home),
                screen = Screen.HomeScreenDriver,
                contentDescription = "home_driver"
            ),

            NavItemDriver(
                title = stringResource(R.string.income),
                icon = painterResource(id = R.drawable.income),
                screen = Screen.IncomeScreenDriver,
                contentDescription = "income_driver"
            ),

            NavItemDriver(
                title = stringResource(R.string.history),
                icon = painterResource(id = R.drawable.history),
                screen = Screen.HistoryScreenDriver,
                contentDescription = "history_driver"
            ),

            NavItemDriver(
                title = stringResource(R.string.setting),
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
                    val iconMod = if (currentRoute == itemDriver.screen.route) {
                        GreenLight
                    } else {
                        Color.Gray
                    }
                    Icon(
                        painter = itemDriver.icon, contentDescription = itemDriver.title,
                        modifier = modifier.size(18.dp),
                        tint = iconMod
                    )
                },
                label = {
                    val labelColor = if (currentRoute == itemDriver.screen.route) {
                        GreenLight
                    } else {
                        Color.Gray
                    }
                    val weight = if (currentRoute == itemDriver.screen.route) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Medium
                    }

                    Text(
                        text = itemDriver.title, style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 9.sp, color = labelColor, fontWeight = weight
                        )
                    )
                }
            )
        }
    }
}