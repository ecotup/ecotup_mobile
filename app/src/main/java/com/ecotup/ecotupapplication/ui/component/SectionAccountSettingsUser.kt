package com.ecotup.ecotupapplication.ui.component

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.ui.theme.GreyLight
import com.ecotup.ecotupapplication.ui.user.setting.SettingUserViewModel
import com.ecotup.ecotupapplication.util.IntentToMain
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun SectionAccountSettingsUser(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(16.dp),
    ) {
        // Edit Profile
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
//                        navController.navigate(Screen.OptionScreen.route)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.edit_profile_icon),
                    contentDescription = "edit_profile_icon",
                    modifier = modifier.size(22.dp)
                )

                SpacerCustom(space = 10)

                Text(
                    text = "Edit Profile", style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(id = R.drawable.arrow_right_icon),
                    contentDescription = "arrow_right_icon",
                    modifier = modifier.size(28.dp)
                )
            }
        }
        SpacerCustom(space = 5)
        Divider(color = GreyLight, thickness = 1.dp)
        SpacerCustom(space = 5)
        // Edit Address
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
//                        navController.navigate(Screen.OptionScreen.route)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.address_setting_icon),
                    contentDescription = "address_setting_icon",
                    modifier = modifier.size(22.dp)
                )

                SpacerCustom(space = 10)

                Text(
                    text = "Address Setting ", style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(id = R.drawable.arrow_right_icon),
                    contentDescription = "arrow_right_icon",
                    modifier = modifier.size(28.dp)
                )
            }
        }
        SpacerCustom(space = 5)
        Divider(color = GreyLight, thickness = 1.dp)
        SpacerCustom(space = 5)
        // About Us
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
//                        navController.navigate(Screen.OptionScreen.route)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.about_icon),
                    contentDescription = "about_icon",
                    modifier = modifier.size(22.dp)
                )

                SpacerCustom(space = 10)

                Text(
                    text = "About Us", style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(id = R.drawable.arrow_right_icon),
                    contentDescription = "arrow_right_icon",
                    modifier = modifier.size(28.dp)
                )
            }
        }
        SpacerCustom(space = 5)
        Divider(color = GreyLight, thickness = 1.dp)
        SpacerCustom(space = 5)
        // Help & Report
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
//                        navController.navigate(Screen.OptionScreen.route)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.help_report_icon),
                    contentDescription = "help_report_icon",
                    modifier = modifier.size(22.dp)
                )

                SpacerCustom(space = 10)

                Text(
                    text = "Help and Report ", style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(id = R.drawable.arrow_right_icon),
                    contentDescription = "arrow_right_icon",
                    modifier = modifier.size(28.dp)
                )
            }
        }
    }
}

@Composable
fun SectionApplicationSettingsUser(viewModel: SettingUserViewModel = viewModel(
    factory = ViewModelFactory.getInstance(LocalContext.current)), modifier: Modifier = Modifier) {

    val localContext = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(16.dp),
    ) {
        // Dark Mode
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.dark_mode_icon),
                    contentDescription = "dark_mode_icon",
                    modifier = modifier.size(22.dp)
                )

                SpacerCustom(space = 10)

                Text(
                    text = "Dark Mode", style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically){
                SwitchDarkMode()
            }
        }
        SpacerCustom(space = 5)
        Divider(color = GreyLight, thickness = 1.dp)
        SpacerCustom(space = 5)
        // Languages
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
//                        navController.navigate(Screen.OptionScreen.route)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.languages_icon),
                    contentDescription = "languages_icon",
                    modifier = modifier.size(22.dp)
                )

                SpacerCustom(space = 10)

                Text(
                    text = "Languages", style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(id = R.drawable.arrow_right_icon),
                    contentDescription = "arrow_right_icon",
                    modifier = modifier.size(28.dp)
                )
            }
        }
        SpacerCustom(space = 5)
        Divider(color = GreyLight, thickness = 1.dp)
        SpacerCustom(space = 5)
        // Logout
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    val sweetAlertDialog =
                        SweetAlertDialog(localContext, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Log Out")
                            .setContentText("Are you sure you want to exit the Ecotup application ?")
                            .setConfirmButton("Yes") {child ->
                                val sweetAlertDialogChild = SweetAlertDialog(localContext, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Log Out")
                                    .setContentText("You have successfully logged out of the Ecotup application")
                                    .setConfirmButton("OK") {child ->
                                        viewModel.logoutUser()
                                        viewModel.deleteSessionUser()
                                        IntentToMain(localContext)
                                        child.dismissWithAnimation()
                                    }
                                sweetAlertDialogChild.setCancelable(true)
                                sweetAlertDialogChild.show()
                            }
                            .setCancelButton("No") {child ->
                                child.dismissWithAnimation()
                            }
                    sweetAlertDialog.setCancelable(true)
                    sweetAlertDialog.show()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.logout_icon),
                    contentDescription = "logout_icon",
                    modifier = modifier.size(22.dp)
                )

                SpacerCustom(space = 10)

                Text(
                    text = "Logout", style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(id = R.drawable.arrow_right_icon),
                    contentDescription = "arrow_right_icon",
                    modifier = modifier.size(28.dp)
                )
            }
        }
    }
}

@Composable
fun SwitchDarkMode() {
    var checked by remember { mutableStateOf(false) }

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        },
        modifier = Modifier.height(28.dp)
    )
}

@Preview
@Composable
fun SectionAccountSettingsUserPrev() {
    SectionApplicationSettingsUser()
}