package com.ecotup.ecotupapplication.ui.driver.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.util.IntentToMain

@Composable
fun SettingScreenDriver(
    viewModel: SettingDriverViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ), modifier: Modifier = Modifier
) {
    val localContext = LocalContext.current
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Setting Screen Driver")
        Button(onClick = {
            viewModel.logoutDriver()
            IntentToMain(localContext)
        }) {
            Text(text = "Log out")
        }
    }
}