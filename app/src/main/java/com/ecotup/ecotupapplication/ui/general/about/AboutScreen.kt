package com.ecotup.ecotupapplication.ui.general.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.ClickableImageBack
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun AboutScreen(
    modifier : Modifier = Modifier, navController: NavController
) {
    Box(modifier = modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.topbar_about),
            contentDescription = "topbar_about",
            modifier = modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        // Button Back
        Column(modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),) {
            SpacerCustom(space = 15)
            Row(
                modifier = modifier
                    .fillMaxWidth(),
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
                Text(
                    text = stringResource(R.string.about_us),
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold
                    )
                )
            }
            SpacerCustom(space = 50)
            Image(
                painter = painterResource(id = R.drawable.ecotup_logo_png),
                contentDescription = "ecotup_logo",
                modifier = modifier
                    .size(254.dp, 64.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop,
            )
            SpacerCustom(space = 20)
            Text(
                text = stringResource(R.string.about_paraf),
                modifier = modifier,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp, color = Color.Black, fontWeight = FontWeight.Normal, textAlign = TextAlign.Justify
                )
            )
        }
    }


    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
        Image(
            painter = painterResource(id = R.drawable.bottombar_about),
            contentDescription = "bottombar_about",
            modifier = modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Version 1.0",
            modifier = modifier.padding(bottom = 20.dp),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2_XL)
@Composable
fun AboutScreenPrev() {
    AboutScreen(navController = rememberNavController())
}