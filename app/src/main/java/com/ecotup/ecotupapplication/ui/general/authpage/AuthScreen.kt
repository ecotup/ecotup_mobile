package com.ecotup.ecotupapplication.ui.general.authpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.SpacerCustom


@Composable
fun AuthScreen(navController: NavController, modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = GreenLight)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .offset(y = (-200).dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ecotup_logo_white_large),
                contentDescription = "logo_white",
                modifier = modifier.width(200.dp)
            )
        }

        Box(
            modifier = modifier
                .align(
                    Alignment.BottomCenter
                )
                .background(
                    color = Color.White, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
                .height(400.dp)
                .padding(24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.hi),
                contentDescription = "hi",
                modifier = modifier
                    .size(250.dp)
                    .align(Alignment.BottomStart)
                    .offset(x = (-85).dp, y = 30.dp)
            )
            // Hello
            Column {
                Column {
                    Text(
                        text = "Hello!", style = MaterialTheme.typography.bodyMedium.copy(
                            color = GreenLight, fontSize = 30.sp, fontWeight = FontWeight.Bold
                        )
                    )
                    SpacerCustom(space = 5)
                    Text(
                        modifier = modifier.fillMaxWidth(),
                        text = "Please authenticate first, before using the application !",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = GreenLight, fontSize = 15.sp, textAlign = TextAlign.Justify
                        ),
                    )
                }
                SpacerCustom(space = 10)
                Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                    //Login with Email
                    ButtonLogin(modifier = modifier,
                        image = R.drawable.email,
                        text = "Login with Email",
                        click = { navController.navigate(Screen.LoginScreen.route) })
                    SpacerCustom(space = 5)
                    //Login with Google
                    ButtonLogin(modifier = modifier,
                        image = R.drawable.google_logo,
                        text = "Login with Google",
                        click = {})

                   SpacerCustom(space = 20)
                    Text(
                        modifier = modifier,
                        text = "You don't have account ?",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = GreenLight, fontSize = 15.sp, textAlign = TextAlign.Right
                        ),
                    )
                    SpacerCustom(space = 5)
                    Text(
                        modifier = modifier
                            .clickable {
                                navController.navigate(Screen.OptionScreen.route)
                            },
                        text = "Register Now !",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = GreenLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Right,
                            textDecoration = TextDecoration.Underline
                        ),
                    )
                }
            }

        }
    }

}

@Composable
private fun ButtonLogin(
    modifier: Modifier = Modifier, image: Int, text: String, click: () -> Unit
) {
    Column(modifier = modifier
        .clickable { click() }
        .width(180.dp)
        .shadow(2.dp, RoundedCornerShape(10.dp))
        ) {
        Row(modifier = modifier.fillMaxWidth().padding(16.dp),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround){
            // Image
            Image(
                painter = painterResource(id = image),
                contentDescription = "google",
                modifier = Modifier.size(20.dp)
            )


            // Text
            Text(
                modifier = modifier,
                text = text,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
        }
    }
}