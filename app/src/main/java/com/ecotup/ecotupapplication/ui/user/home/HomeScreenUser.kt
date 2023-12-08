package com.ecotup.ecotupapplication.ui.user.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.component.SectionAddressDashboardUser
import com.ecotup.ecotupapplication.ui.component.SectionArticleDashboardUser
import com.ecotup.ecotupapplication.ui.component.SectionMainMenuDashboardUser
import com.ecotup.ecotupapplication.ui.component.SectionPointDashboardUser
import com.ecotup.ecotupapplication.ui.component.SectionProfileDashboardUser
import com.ecotup.ecotupapplication.ui.component.SectionRemainingSubscriptionDasboardUser
import com.ecotup.ecotupapplication.ui.theme.EcotupApplicationTheme
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun HomeScreenUser(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize())
    {
        Image(
            painter = painterResource(id = R.drawable.topbar),
            contentDescription = "topbar",
            modifier = modifier.fillMaxWidth()
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        {
            SpacerCustom(space = 10)

            Image(
                painter = painterResource(id = R.drawable.ecotup_logo_white_large),
                contentDescription = "logo_white",
                modifier = modifier
                    .width(150.dp)
                    .align(Alignment.CenterHorizontally)
            )

            SpacerCustom(space = 15)

            SectionProfileDashboardUser()

            SpacerCustom(space = 5)

            SectionAddressDashboardUser()

            SpacerCustom(space = 6)

            LazyColumn(contentPadding = PaddingValues(bottom = 16.dp))
            {
                item {
                    SectionPointDashboardUser()

                    SpacerCustom(space = 6)

                    SectionRemainingSubscriptionDasboardUser()

                    SpacerCustom(space = 6)

                    SectionMainMenuDashboardUser()

                    SpacerCustom(space = 6)

//                    // Contoh
//                    SectionPointDashboardUser()
//
//                    SpacerCustom(space = 6)
//
//                    SectionRemainingSubscriptionDasboardUser()
//
//                    SpacerCustom(space = 6)
//
//                    SectionMainMenuDashboardUser()
//
//                    SpacerCustom(space = 6)
//
//                    // Contoh
//                    SectionPointDashboardUser()
//
//                    SpacerCustom(space = 6)
//
//                    SectionRemainingSubscriptionDasboardUser()
//
//                    SpacerCustom(space = 6)
//
//                    SectionMainMenuDashboardUser()
//
//                    SpacerCustom(space = 6)

                    SectionArticleDashboardUser()
                }
            }


        }

    }
}

@Composable
@Preview
fun HomeScreenUserPreview() {
    EcotupApplicationTheme {
        HomeScreenUser()
    }
}