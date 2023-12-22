package com.ecotup.ecotupapplication.ui.component

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.util.SpacerCustom
import com.ecotup.ecotupapplication.util.getReadableLocation

@Composable
fun ListComponent(
    modifier: Modifier = Modifier,
    image: String,
    lat: Double,
    long: Double,
    context: Context,
    onClickItem: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                2.dp,
                RoundedCornerShape(10.dp)
            )
            .background(
                Color.White,
                shape = RoundedCornerShape(
                    10.dp
                )
            )
            .padding(16.dp)
            .clickable {
                onClickItem()
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = image,
            contentDescription = stringResource(R.string.photo_driver),
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.profile_temp),
            modifier = Modifier
                .size(30.dp)
                .clip(
                    CircleShape
                )

        )
        SpacerCustom(space = 5)
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            Image(
                painter = painterResource(id = R.drawable.location_node_90_x_90),
                contentDescription = stringResource(R.string.profile),
                modifier = Modifier.size(20.dp)
            )
            SpacerCustom(space = 10)
            Text(
                text = getReadableLocation(context = context, latitude = lat, longitude = long),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

