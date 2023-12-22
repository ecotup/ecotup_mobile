package com.ecotup.ecotupapplication.ui.user.reward

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.ClickableImageBack
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun DetailRewardScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    image: String,
    name: String,
    price: String,
    description: String
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(65.dp)
                .background(GreenLight, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
        ) {

        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val painterBack = painterResource(id = R.drawable.button_back_white)
                ClickableImageBack(
                    painter = painterBack,
                    contentDescription = "Back",
                    onClick = {
                        navController.popBackStack()
                    },
                    35,
                    35
                )
                SpacerCustom(space = 5)
                Text(
                    text = "Detail Reward", style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        letterSpacing = 0.003.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
            SpacerCustom(space = 15)

            LazyColumn(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    Image(
                        painter = rememberAsyncImagePainter(model = image),
                        contentDescription = "image_reward",
                        modifier = modifier.size(184.dp)
                    )

                    SpacerCustom(space = 10)

                    Text(
                        text = name, style = MaterialTheme.typography.bodyMedium.copy(
                            color = GreenLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            letterSpacing = 0.003.sp
                        )
                    )

                    SpacerCustom(space = 10)

                    Row() {
                        Text(
                            text = "$price Point", style = MaterialTheme.typography.bodyMedium.copy(
                                color = GreenLight,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                letterSpacing = 0.003.sp
                            )
                        )
                    }
                    SpacerCustom(space = 10)
                    Text(
                        text = description, style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Black,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp,
                            letterSpacing = 0.003.sp,
                            textAlign = TextAlign.Justify
                        )
                    )

                    SpacerCustom(space = 15)

                    Button(
                        onClick = {
                            navController.navigate(Screen.SuccessGetRewardScreen.route)
                        }, modifier = modifier
                            .fillMaxWidth()
                            .height(42.dp)
                    ) {
                        Text(
                            text = "Exchange", style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                letterSpacing = 0.003.sp
                            )
                        )
                    }
                }
            }

        }
    }
}
