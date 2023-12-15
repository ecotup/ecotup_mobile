package com.ecotup.ecotupapplication.ui.user.reward

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.ClickableImageBack
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun DetailRewardScreen(modifier : Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.topbar),
            contentDescription = "topbar",
            modifier = modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(30.dp),
        ) {
            SpacerCustom(space = 30)
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                val painterBack = painterResource(id = R.drawable.button_back_white)
                ClickableImageBack(
                    painter = painterBack,
                    contentDescription = "Back",
                    onClick = {
//                        navController.navigate(Screen.OptionScreen.route)
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

            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Image(painter = painterResource(id = R.drawable.tote_bag_image), contentDescription = "reward_tote_bag", modifier = modifier.size(184.dp))

                SpacerCustom(space = 10)

                Text(
                    text = "Tote Bag", style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        letterSpacing = 0.003.sp
                    )
                )

                SpacerCustom(space = 20)

                Row(){
                    Text(
                        text = "1000 Point", style = MaterialTheme.typography.bodyMedium.copy(
                            color = GreenLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            letterSpacing = 0.003.sp
                        )
                    )
                }
                SpacerCustom(space = 10)
                Text(
                    text = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolores esse molestias possimus dolorem voluptatibus ipsa eum est eligendi dolorum numquam ut, mollitia dignissimos modi omnis, temporibus blanditiis sapiente, magni officiis doloremque commodi quod nam. Quia autem sint et deleniti, recusandae perspiciatis iste eaque, commodi rerum est, blanditiis cum laudantium porro voluptate doloremque? Nihil, molestias molestiae eum eveniet ut rerum, maxime earum autem nemo aut consequatur illo ullam reprehenderit distinctio, perspiciatis laudantium tempora? \n", style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        letterSpacing = 0.003.sp,
                        textAlign = TextAlign.Justify
                    )
                )

                SpacerCustom(space = 15)

                Button(onClick = { /*TODO*/ }, modifier = modifier.fillMaxWidth().height(42.dp)) {
                    Text(
                        text = "Exchange", style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold, fontSize = 15.sp, letterSpacing = 0.003.sp
                        )
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailRewardPrev() {
    DetailRewardScreen()
}