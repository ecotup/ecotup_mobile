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
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.theme.BlueLight
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.ui.theme.GreyLight
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun CardTripDetails(modifier: Modifier = Modifier) {
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
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = stringResource(R.string.trip_details),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }
        }
        SpacerCustom(space = 10)
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.form_point),
                    contentDescription = stringResource(R.string.point_address_green),
                    modifier = modifier
                        .size(24.dp)
                )

                SpacerCustom(space = 5)

                Column {
                    Text(
                        text = stringResource(R.string.from),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = GreyLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            letterSpacing = 0.003.sp
                        )
                    )
                    SpacerCustom(space = 2)
                    Text(
                        text = stringResource(R.string.temp_address),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = GreenLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            letterSpacing = 0.003.sp
                        )
                    )
                }
            }
        }
        SpacerCustom(space = 5)
        Divider(color = GreyLight, thickness = 1.dp)
        SpacerCustom(space = 5)
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.to_point),
                    contentDescription = stringResource(id = R.string.point_address_green),
                    modifier = modifier
                        .size(24.dp)
                )

                SpacerCustom(space = 5)
                Column {
                    Text(
                        text = stringResource(R.string.to),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = GreyLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            letterSpacing = 0.003.sp
                        )
                    )
                    SpacerCustom(space = 2)
                    Text(
                        text = stringResource(R.string.tempat_pembuangan_akhir),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = BlueLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            letterSpacing = 0.003.sp
                        )
                    )
                }
            }
        }
    }
}