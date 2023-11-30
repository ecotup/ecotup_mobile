package com.ecotup.ecotupapplication.ui.general.splashScreen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ecotup.ecotupapplication.MainActivity
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.util.SPLASH_SCREEN_DURATION
import com.ecotup.ecotupapplication.util.getDrawableFromResource
import com.ecotup.ecotupapplication.util.getRememberAsyncImagePainter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//@SuppressLint("CustomSplashScreen")
class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SplashScreenContent(modifier = Modifier)
        }

        lifecycleScope.launch {
            delay(SPLASH_SCREEN_DURATION)
            nextScreen(this@SplashScreen)
        }
    }

    @Composable
    fun SplashScreenContent(modifier: Modifier = Modifier) {
        val context = LocalContext.current
        val imageDrawable = getDrawableFromResource(context, R.drawable.ecotup_logo_tagline)
        val painterLogo = getRememberAsyncImagePainter(context = context, data = imageDrawable)
        val imageDrawableBackground = getDrawableFromResource(context, R.drawable.background_doodle)
        val painterBackground = getRememberAsyncImagePainter(context = context, data = imageDrawableBackground)

        Box(
            modifier = modifier.fillMaxSize()
        ) {
            Image(
                painter = painterLogo,
                contentDescription = "Logo Ecotup",
                modifier = modifier.fillMaxSize()
            )

            Image(
                painter = painterBackground,
                contentDescription = "Background Ecotup",
                modifier = modifier.fillMaxSize()
            )
        }
    }

    private fun nextScreen(originActivity: Activity) {
        val intent = Intent(originActivity, MainActivity::class.java)
        startActivity(intent)
        originActivity.finish()
    }
}

