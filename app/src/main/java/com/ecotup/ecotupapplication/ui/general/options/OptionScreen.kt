package com.ecotup.ecotupapplication.ui.general.options

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun OptionScreen(navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        SpacerCustom(space = 30)
        LogoEcotup(modifier = modifier, context = context)
        SpacerCustom(space = 30)
        Row(modifier = modifier) {
            Text(
                text = "Which one are you : ", style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 18.sp, color = GreenLight, fontWeight = FontWeight.Bold
                )
            )
        }
        SpacerCustom(space = 10)
        Row() {
            Option(
                context = context,
                imageDrawable = R.drawable.user_image,
                text = "I'm a User",
                modifier = modifier
            )
        }
        SpacerCustom(space = 10)
        Row {
            Option(
                context = context,
                imageDrawable = R.drawable.driver_image,
                text = "I'm a Driver",
                modifier = modifier
            )
        }
        SpacerCustom(space = 30)
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    navController.navigate(Screen.LoginUserScreen.route)
                },
                modifier = modifier

            ) {
                Text(
                    text = "Next", style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp, color = Color.White, fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}


@Composable
fun LogoEcotup(modifier: Modifier, context: Context) {
    val imageEcotup = R.drawable.ecotup_512_250_px
    val painterEcotup = painterResource(imageEcotup)
    Row(modifier = modifier.fillMaxWidth()) {
        Image(
            painter = painterEcotup,
            contentDescription = "Logo Ecotup",
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Composable
fun Option(context: Context, imageDrawable: Int, text: String, modifier: Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.Gray.copy(alpha = 0.3f), shape = MaterialTheme.shapes.small)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier
        ) {
            val painterImg = painterResource(id = imageDrawable)
            Image(painter = painterImg, contentDescription = "logo")
        }

        Box(
            modifier = modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text, style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                )
            )
        }
    }
}