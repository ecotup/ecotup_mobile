package com.ecotup.ecotupapplication.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun SectionHistoryUser(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(16.dp),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.one_time_history),
                    contentDescription = "one_time",
                    modifier = modifier
                        .size(35.dp, 35.dp)
                )

                SpacerCustom(space = 5)

                Column {
                    Text(
                        text = "One Time", style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            letterSpacing = 0.003.sp
                        )
                    )

                    Text(
                        text = "October, 28 2023", style = MaterialTheme.typography.bodyMedium.copy(
                            color = GreenLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            letterSpacing = 0.003.sp
                        )
                    )
                }
            }
            Row (verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Rp. 14.000, 00", style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        letterSpacing = 0.003.sp
                    )
                )
                SpacerCustom(space = 5)
            }
        }

        Row(modifier = modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Row(verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(id = R.drawable.form_point),
                    contentDescription = "point_address",
                    modifier = modifier
                        .size(15.dp)
                )

                SpacerCustom(space = 2)

                Text(
                    text = "Jalan Jendral Sudirman No. 20 ", style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically){
                Button(onClick = { /*TODO*/ }, modifier = modifier.size(100.dp, 30.dp)) {
                    Text(
                        text = "See details", style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold, fontSize = 12.sp, letterSpacing = 0.003.sp
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SectionHistoryUserPreview() {
    SectionHistoryUser()
}
