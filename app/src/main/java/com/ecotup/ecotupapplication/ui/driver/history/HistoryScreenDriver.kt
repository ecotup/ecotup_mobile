package com.ecotup.ecotupapplication.ui.driver.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.component.SectionHistoryDriver
import com.ecotup.ecotupapplication.ui.component.SectionHistoryUser
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun HistoryScreenDriver(modifier : Modifier = Modifier, navController: NavController)
{
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_doodle),
            contentDescription = "background_doodle",
            modifier = modifier.fillMaxSize()
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(75.dp)
                .background(GreenLight, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
        ) {

        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SpacerCustom(space = 5)
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ){
                Image(
                    painter = painterResource(id = R.drawable.time_icon_white),
                    contentDescription = "time_icon_history",
                    modifier = modifier.size(22.dp),
                )
                SpacerCustom(space = 5)
                Text(
                    text = stringResource(R.string.history), style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }

            SpacerCustom(space = 25)
            LazyColumn(modifier = modifier.fillMaxSize())
            {
                items(1) { index ->
                    SectionHistoryDriver(navController = navController)
                    SpacerCustom(space = 5)
                }
            }
        }
    }
}