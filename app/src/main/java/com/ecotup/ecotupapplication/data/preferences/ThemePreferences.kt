package com.ecotup.ecotupapplication.data.preferences

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ecotup.ecotupapplication.ui.theme.EcotupApplicationTheme

@Composable
fun SwitchDarkMode() {
    var darkTheme by remember { mutableStateOf(false) }

    EcotupApplicationTheme(darkTheme = darkTheme) {
        Switch(
            checked = darkTheme,
            onCheckedChange = {
                darkTheme = it
            }
        )
    }
}