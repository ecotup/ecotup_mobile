package com.ecotup.ecotupapplication.ui.navigation

import androidx.compose.ui.graphics.painter.Painter

data class NavItemDriver(
    val title: String,
    val icon: Painter,
    val screen: Screen,
    val contentDescription: String
)