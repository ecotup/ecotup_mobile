package com.ecotup.ecotupapplication.ui.driver.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ecotup.ecotupapplication.ui.navigation.Screen

@Composable
fun HomeScreenDriver(modifier: Modifier = Modifier)
{
    Column(modifier = modifier.fillMaxSize())
    {
        Text(text ="Home Screen Driver")
    }
}