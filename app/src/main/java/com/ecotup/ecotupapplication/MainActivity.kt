package com.ecotup.ecotupapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ecotup.ecotupapplication.ui.navigation.Navigation
import com.ecotup.ecotupapplication.ui.theme.EcotupApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcotupApplicationTheme {
                Navigation()
            }
        }
    }
}

