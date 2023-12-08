package com.ecotup.ecotupapplication.ui.user.setting

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ecotup.ecotupapplication.MainActivity
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.ui.general.login.LoginViewModel
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.util.IntentToMain
import kotlinx.coroutines.runBlocking

@Composable
fun SettingScreenUser(
    viewModel: SettingUserViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)),
    modifier: Modifier = Modifier
) {
    val localContext = LocalContext.current

    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Setting Screen User")
        Button(onClick = {
            viewModel.logoutUser()
            IntentToMain(localContext)
        }) {
            Text(text = "Log out")
        }
    }
}

