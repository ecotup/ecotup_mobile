package com.ecotup.ecotupapplication.ui.driver.income

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun IncomeScreen(modifier : Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize())
    {
        Text(text ="Income Screen Driver")
    }
}