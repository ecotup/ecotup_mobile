package com.ecotup.ecotupapplication.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import coil.compose.AsyncImage
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.driver.home.HomeDriverViewModel
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun SectionProfileDashboardDriver(
    viewModel: HomeDriverViewModel,
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner
) {
    var photo by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var point by remember {
        mutableStateOf("")
    }

    viewModel.getSessionDriver().observe(lifecycleOwner) {
        photo = it.profile
        name = it.name
        point = it.point
    }

    val member = when (point.toInt()) {
        in 0..200 -> R.drawable.silver
        in 201..500 -> R.drawable.gold
        in 501..1000 -> R.drawable.platinum
        else -> R.drawable.diamond
    }
    if (point.isEmpty()) {
        point = "0"
    }

    val descMember = when (point.toInt()) {
        in 0..200 -> "Silver"
        in 201..500 -> "Gold"
        in 501..1000 -> "Platinum"
        else -> "Diamond"
    }


    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            AsyncImage(
                model = photo,
                contentDescription = stringResource(id = R.string.photo_driver),
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.profile_driver),
                modifier = modifier
                    .size(45.dp)
                    .padding(2.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape),
            )

            SpacerCustom(space = 5)

            Column(modifier = modifier) {
                //  Hello
                Text(
                    text = stringResource(R.string.hello),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )

                // Nama Driver
                Text(
                    text = name, style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically)
        {
            AsyncImage(
                model = member,
                contentDescription = stringResource(R.string.member),
                error = painterResource(R.drawable.silver),
                modifier = modifier
                    .size(30.dp)
                    .padding(2.dp),
            )
            SpacerCustom(space = 5)
            Text(
                text = descMember, style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
            SpacerCustom(space = 5)
        }
    }
}
