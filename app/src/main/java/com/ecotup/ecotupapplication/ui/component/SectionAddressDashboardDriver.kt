package com.ecotup.ecotupapplication.ui.component

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.driver.home.HomeDriverViewModel
import com.ecotup.ecotupapplication.ui.user.home.HomeUserViewModel
import com.ecotup.ecotupapplication.util.SpacerCustom
import com.ecotup.ecotupapplication.util.getCurrentLocation
import com.ecotup.ecotupapplication.util.getReadableLocation

@Composable
fun SectionAddressDashboardDriver(viewModel : HomeDriverViewModel,modifier: Modifier = Modifier, lifecycleOwner: LifecycleOwner) {
    var lat by remember {
        mutableDoubleStateOf(0.0)
    }

    var long by remember {
        mutableDoubleStateOf(0.0)
    }

//    viewModel.getSessionUser().observe(lifecycleOwner){
//        lat = it.lat
//        long = it.long
//
//    }

    getCurrentLocation(context = lifecycleOwner as Context){
        lat = it.latitude
        long = it.longitude
    }

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        SpacerCustom(space = 3)
        // Point Address
        Image(
            painter = painterResource(id = R.drawable.point_address),
            contentDescription = "point_address",
            modifier = modifier.size(22.dp)
        )
        
        SpacerCustom(space = 5)

        // Address
        Text(
            text = getReadableLocation(lat, long, lifecycleOwner as Context),
            modifier = modifier,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp, letterSpacing = 0.003.sp
            )
        )
    }


}