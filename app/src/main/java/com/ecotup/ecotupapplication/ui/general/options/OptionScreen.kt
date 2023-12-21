package com.ecotup.ecotupapplication.ui.general.options

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.ecotup.ecotupapplication.util.sweetAlert

@Composable
fun OptionScreen(navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var user by remember {
        mutableStateOf(false)
    }
    var driver by remember {
        mutableStateOf(false)
    }
    LazyColumn {
        item {
            Column(
                modifier = modifier
                    .padding(16.dp)
            ) {
                SpacerCustom(space = 50)
                LogoEcotup(modifier = modifier, context = context)
                SpacerCustom(space = 50)
                Row(modifier = modifier) {
                    Text(
                        text = "Which one are you : ",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 18.sp, color = GreenLight, fontWeight = FontWeight.Bold
                        )
                    )
                }
                SpacerCustom(space = 10)
                Row {
                    Button(
                        onClick = {
                            if (!user) {
                                user = true
                                driver = false
                            }
                        },
                        modifier = modifier
                            .fillMaxWidth()
                            .border(
                                if (user) 3.dp else 1.dp,
                                if (user) GreenLight else Color.Gray,
                                MaterialTheme.shapes.medium
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = GreenLight
                        )
                    ) {
                        Box(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            val painterImg = painterResource(id = R.drawable.user_image)
                            Image(
                                painter = painterImg,
                                contentDescription = "logo",
                                modifier = modifier.align(Alignment.CenterStart)
                            )
                            Text(
                                text = "I'm a User",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 15.sp,
                                    color = GreenLight,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            if (user) {
                                val painterCheck =
                                    painterResource(id = R.drawable.checklist_90_x_90)
                                Image(
                                    painter = painterCheck,
                                    contentDescription = "check",
                                    modifier = modifier
                                        .align(Alignment.CenterEnd)
                                        .size(25.dp)
                                )
                            }

                        }
                    }
                }
                SpacerCustom(space = 10)
                Row {
                    Button(
                        onClick = {
                            if (!driver) {
                                driver = true
                                user = false
                            }
                        },
                        modifier = modifier
                            .fillMaxWidth()
                            .border(
                                if (driver) 3.dp else 1.dp,
                                if (driver) GreenLight else Color.Gray,
                                MaterialTheme.shapes.medium
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = GreenLight
                        )
                    ) {
                        Box(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            val painterImg = painterResource(id = R.drawable.driver_image)
                            Image(
                                painter = painterImg,
                                contentDescription = "logo",
                                modifier = modifier.align(Alignment.CenterStart)
                            )
                            Text(
                                text = "I'm a Driver",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 15.sp,
                                    color = GreenLight,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            if (driver) {
                                val painterCheck =
                                    painterResource(id = R.drawable.checklist_90_x_90)
                                Image(
                                    painter = painterCheck,
                                    contentDescription = "check",
                                    modifier = modifier
                                        .align(Alignment.CenterEnd)
                                        .size(25.dp)
                                )
                            }
                        }
                    }
                }
                SpacerCustom(space = 30)
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        //atur route ketika next
                        onClick = {
                            if (!user && !driver) {
                                sweetAlert(
                                    context = context,
                                    title = "Warning",
                                    contentText = "Please choose one",
                                    type = "warning",
                                    isCancel = true
                                )
                            } else {
                                if (user) {
                                    navController.navigate(Screen.RegisterUserScreen.route)
                                } else {
                                    navController.navigate(Screen.RegisterDriverScreen.route)
                                }
                            }
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
    }

}

@Composable
fun LogoEcotup(modifier: Modifier, context: Context) {
    val imageEcotup = R.drawable.ecotup_logo_large
    val painterEcotup = painterResource(imageEcotup)
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Image(
            painter = painterEcotup,
            contentDescription = "Logo Ecotup",
            modifier = modifier.width(150.dp)
        )
    }
}
