package com.ecotup.ecotupapplication.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun CardRewardUser(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .size(160.dp, 200.dp)
            .shadow(4.dp, RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
    ) {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = R.drawable.background_doodle), contentDescription = "background_doodle", modifier = modifier.size(160.dp, 120.dp).background(color = Color.White, shape = RoundedCornerShape(10.dp)))
            Image(painter = painterResource(id = R.drawable.tote_bag_image), contentDescription = "reward_tote_bag")
        }

        Column(modifier = modifier
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
            SpacerCustom(space = 2)

            Text(text = "Tote Bag", style = MaterialTheme.typography.bodyMedium.copy(
                color = GreenLight,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                letterSpacing = 0.003.sp))

            SpacerCustom(space = 2)

            Text(text = "1000 Point", style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                letterSpacing = 0.003.sp))

            SpacerCustom(space = 2)

            Button(onClick = { /*TODO*/ }, modifier = modifier.size(136.dp, 32.dp)) {
                Text(
                    text = "Exchange", style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = 0.003.sp
                    )
                )
            }
        }


    }

}

@Preview
@Composable
fun CardRewardPrev() {
    CardRewardUser()
}