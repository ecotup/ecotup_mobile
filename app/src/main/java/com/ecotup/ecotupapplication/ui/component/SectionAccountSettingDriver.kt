package com.ecotup.ecotupapplication.ui.component

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.preferences.SwitchDarkMode
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.ui.driver.setting.SettingDriverViewModel
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.ui.theme.GreyLight
import com.ecotup.ecotupapplication.util.IntentToMain
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun SectionAccountSettingsDriver(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(16.dp),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(Screen.EditProfileScreenDriver.route)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.edit_profile_icon),
                    contentDescription = stringResource(R.string.edit_profile_icon),
                    modifier = modifier.size(22.dp)
                )

                SpacerCustom(space = 10)

                Text(
                    text = stringResource(R.string.edit_profile),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_right_icon),
                    contentDescription = stringResource(R.string.arrow_right_icon),
                    modifier = modifier.size(28.dp)
                )
            }
        }
        SpacerCustom(space = 5)
        Divider(color = GreyLight, thickness = 1.dp)
        SpacerCustom(space = 5)
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(Screen.EditAddressScreenDriver.route)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.address_setting_icon),
                    contentDescription = stringResource(R.string.address_setting_icon),
                    modifier = modifier.size(22.dp)
                )

                SpacerCustom(space = 10)

                Text(
                    text = stringResource(R.string.address_setting),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_right_icon),
                    contentDescription = stringResource(id = R.string.arrow_right_icon),
                    modifier = modifier.size(28.dp)
                )
            }
        }
        SpacerCustom(space = 5)
        Divider(color = GreyLight, thickness = 1.dp)
        SpacerCustom(space = 5)

        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(Screen.AboutScreen.route)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.about_icon),
                    contentDescription = stringResource(R.string.about_icon),
                    modifier = modifier.size(22.dp)
                )

                SpacerCustom(space = 10)

                Text(
                    text = stringResource(R.string.about_us),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_right_icon),
                    contentDescription = stringResource(id = R.string.arrow_right_icon),
                    modifier = modifier.size(28.dp)
                )
            }
        }
        SpacerCustom(space = 5)
        Divider(color = GreyLight, thickness = 1.dp)
        SpacerCustom(space = 5)

        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable {

                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.help_report_icon),
                    contentDescription = stringResource(R.string.help_report_icon),
                    modifier = modifier.size(22.dp)
                )

                SpacerCustom(space = 10)

                Text(
                    text = stringResource(R.string.help_and_report),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_right_icon),
                    contentDescription = stringResource(id = R.string.arrow_right_icon),
                    modifier = modifier.size(28.dp)
                )
            }
        }
    }
}

@Composable
fun SectionApplicationSettingsDriver(
    viewModel: SettingDriverViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ), modifier: Modifier = Modifier
) {

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
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.dark_mode_icon),
                    contentDescription = stringResource(R.string.dark_mode_icon),
                    modifier = modifier.size(22.dp)
                )

                SpacerCustom(space = 10)

                Text(
                    text = stringResource(R.string.dark_mode),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
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
                    val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                    localContext.startActivity(intent)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.languages_icon),
                    contentDescription = stringResource(R.string.languages_icon),
                    modifier = modifier.size(22.dp)
                )

                SpacerCustom(space = 10)

                Text(
                    text = stringResource(R.string.languages),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_right_icon),
                    contentDescription = stringResource(id = R.string.arrow_right_icon),
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
                            .setConfirmButton("Yes") { child ->
                                val sweetAlertDialogChild =
                                    SweetAlertDialog(localContext, SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Log Out")
                                        .setContentText("You have successfully logged out of the Ecotup application")
                                        .setConfirmButton("OK") { child ->
                                            viewModel.logoutDriver()
                                            viewModel.deleteSessionDriver()
                                            IntentToMain(localContext)
                                            child.dismissWithAnimation()
                                        }
                                sweetAlertDialogChild.setCancelable(true)
                                sweetAlertDialogChild.show()
                            }
                            .setCancelButton("No") { child ->
                                child.dismissWithAnimation()
                            }
                    sweetAlertDialog.setCancelable(true)
                    sweetAlertDialog.show()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.logout_icon),
                    contentDescription = "logout_icon",
                    modifier = modifier.size(22.dp)
                )

                SpacerCustom(space = 10)

                Text(
                    text = stringResource(R.string.logout),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_right_icon),
                    contentDescription = stringResource(id = R.string.arrow_right_icon),
                    modifier = modifier.size(28.dp)
                )
            }
        }
    }
}