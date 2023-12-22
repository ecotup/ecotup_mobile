package com.ecotup.ecotupapplication.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.driver.setting.SettingDriverViewModel
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.ui.theme.GreyLight
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun CardProfileSettingDriver(
    viewModel: SettingDriverViewModel,
    modifier: Modifier = Modifier, lifecycleOwner: LifecycleOwner
) {
    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    LaunchedEffect(viewModel)
    {
        viewModel.getSessionUser().observe(lifecycleOwner) {
//            photo = it.profile
            name = it.name
            email = it.email
        }
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(16.dp),
    ) {
        Row(modifier = modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Row(verticalAlignment = Alignment.CenterVertically){

                Text(
                    text = stringResource(R.string.driver_ecotup), style = MaterialTheme.typography.bodyMedium.copy(
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
        ){
            Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.profile_driver),
                    contentDescription = "profile_temp",
                    modifier = modifier
                        .size(84.dp)
                        .padding(2.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )

                SpacerCustom(space = 5)

                Column {
                    Text(
                        text = name, style = MaterialTheme.typography.bodyMedium.copy(
                            color = GreenLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            letterSpacing = 0.003.sp
                        )
                    )
                    SpacerCustom(space = 4)
                    Divider(color = GreyLight, thickness = 1.dp)
                    SpacerCustom(space = 4)
                    Text(
                        text = email, style = MaterialTheme.typography.bodyMedium.copy(
                            color = GreenLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            letterSpacing = 0.003.sp
                        )
                    )
                }
            }
        }
    }
}