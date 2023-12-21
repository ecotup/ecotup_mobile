package com.ecotup.ecotupapplication.ui.general.splashScreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.ecotup.ecotupapplication.MainActivity
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.ui.navigation.NavigationViewModel
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.SPLASH_SCREEN_DURATION
import com.ecotup.ecotupapplication.util.SpacerCustom
import com.ecotup.ecotupapplication.util.getDrawableFromResource
import com.ecotup.ecotupapplication.util.getRememberAsyncImagePainter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//@SuppressLint("CustomSplashScreen")
class SplashScreen : ComponentActivity() {
    private val viewModel by viewModels<NavigationViewModel>{
        ViewModelFactory.getInstance(this)
    }
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
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.background_doodle),
                contentDescription = "Background Ecotup",
                modifier = modifier.fillMaxSize()
            )
            Image(
                painter = painterResource(id = R.drawable.ecotup_logo_simple),
                contentDescription = "Logo Ecotup",
                modifier = modifier
                    .size(150.dp)
                    .align(Alignment.Center)
            )

            Column(
                modifier = modifier
                    .height(200.dp)
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )
            {
                Text(text = "From", style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                    color = GreenLight, textAlign = TextAlign.Center
                ), modifier = modifier.fillMaxWidth())
                SpacerCustom(space = 3)
                Text(text = "CH2-PS052", style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.quicksand_bold)),
                        fontWeight = FontWeight.Bold,
                        color = GreenLight,
                        textAlign = TextAlign.Center
                    ), modifier = modifier.fillMaxWidth()
                )
            }
        }
    }

    private fun nextScreen(originActivity: Activity) {
        runBlocking {
            viewModel.getSessionToken().observe(this@SplashScreen){
                Toast.makeText(this@SplashScreen, "Session Token: ${it.role}", Toast.LENGTH_SHORT).show()
            }
        }
        val intent = Intent(originActivity, MainActivity::class.java)
        startActivity(intent)
        originActivity.finish()
    }
}

