package com.ecotup.ecotupapplication.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.theme.EcotupApplicationTheme
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun SectionProfileDashboardUser(modifier: Modifier = Modifier, photo: String = "") {
    Row (modifier = modifier, verticalAlignment = Alignment.CenterVertically){
        AsyncImage(
            model = photo,
            contentDescription = "photo_user",
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.profile_temp),
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
                text = "Hello,", style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )

            // Nama User
            Text(
                text = "Ecotup Name,", style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }

    }

}

@Preview()
@Composable
fun SectionProfileDashboardUserPreview() {
    EcotupApplicationTheme {
        SectionProfileDashboardUser()
    }
}