package com.ecotup.ecotupapplication.ui.user.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.component.CardProfileSetting
import com.ecotup.ecotupapplication.ui.component.SectionAccountSettingsUser
import com.ecotup.ecotupapplication.ui.component.SectionApplicationSettingsUser
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun SettingScreenUser(modifier: Modifier = Modifier) {

    LazyColumn(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            SpacerCustom(space = 20)

            Image(
                painter = painterResource(id = R.drawable.ecotup_logo_png),
                contentDescription = "logo_white",
                modifier = modifier
                    .size(180.dp, 45.dp)
            )
            SpacerCustom(space = 20)

            CardProfileSetting()

            SpacerCustom(space = 15)

            Text(text = "Account Settings", style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                letterSpacing = 0.003.sp))

            SpacerCustom(space = 10)

            SectionAccountSettingsUser()

            SpacerCustom(space = 15)

            Text(text = "Application Settings", style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                letterSpacing = 0.003.sp))

            SpacerCustom(space = 10)

            SectionApplicationSettingsUser()

            SpacerCustom(space = 10)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingUserPrev() {
    SettingScreenUser()
}

