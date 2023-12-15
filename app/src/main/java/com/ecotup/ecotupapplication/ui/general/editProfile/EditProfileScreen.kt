package com.ecotup.ecotupapplication.ui.general.editProfile

import android.content.Context
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ecotup.ecotupapplication.R
//import com.ecotup.ecotupapplication.ui.general.login.LoginForm
//import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.ClickableImageBack
import com.ecotup.ecotupapplication.util.SpacerCustom
import com.ecotup.ecotupapplication.util.sweetAlert

@Composable
fun EditProfileScreen(
    modifier : Modifier = Modifier
) {
    val context = LocalContext.current
    Box(modifier = modifier.padding(16.dp)){


        // Button Back
        Column {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                val painterBack = painterResource(id = R.drawable.button_back)
                ClickableImageBack(
                    painter = painterBack,
                    contentDescription = "Back",
                    onClick = {
//                        navController.navigate(Screen.OptionScreen.route)
                    },
                    35,
                    35
                )
                Text(
                    text = "Edit Profile",
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp, color = GreenLight, fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        EditProfileForm(modifier = Modifier, context = context)

        // Login Form
//        LoginForm(
//            modifier = modifier,
//            context = context,
//            navController = navController,
//            viewModel = viewModel
//        )
}
}

@Composable
fun EditProfileForm(modifier: Modifier, context: Context) {
    var textFullname by remember {
        mutableStateOf("")
    }
    var textEmail by remember {
        mutableStateOf("")
    }
    var isEmailValid by remember {
        mutableStateOf(false)
    }
    var textPhoneNumber by remember {
        mutableStateOf("")
    }
    var textPassword by remember {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(false)
    }

    LazyColumn {
        item {
            Column(modifier = modifier
                .fillMaxSize() ) {
                SpacerCustom(space = 40)
                Text(text = "MEMBER", style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Bold
                ), modifier = modifier.align(Alignment.CenterHorizontally))
                SpacerCustom(space = 4)
                Image(painter = painterResource(id = R.drawable.ecotup_logo_png), contentDescription = "logo_ecotup", modifier = modifier
                    .size(100.dp, 20.dp)
                    .align(Alignment.CenterHorizontally))
                SpacerCustom(space = 20)
                Box(modifier = modifier.align(Alignment.CenterHorizontally)){
                    Image(
                        painter = painterResource(id = R.drawable.profile_temp),
//                    model = photo,
//                    model = "",
                        contentDescription = "photo_user",
                        contentScale = ContentScale.Crop,
//                    error = painterResource(R.drawable.profile_temp),
                        modifier = modifier
                            .size(114.dp)
                            .padding(2.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape)
                    )
                    Image(painter = painterResource(id = R.drawable.edit_icon), contentDescription = "edit_icon_image", modifier = modifier.align(
                        Alignment.BottomEnd).size(25.dp))
                }
                
                SpacerCustom(space = 5)
                Text(text = "Your Picture", style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp, color = GreenLight, fontWeight = FontWeight.Bold
                ), modifier = modifier.align(Alignment.CenterHorizontally))

                SpacerCustom(space = 10)

                // Fullname
                Text(
                    text = "Fullname",
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                    )
                )
                SpacerCustom(space = 5)
                OutlinedTextField(
                    value = textFullname,
                    onValueChange = {
                        textFullname = it
                    },
                    placeholder = {
                        Text(
                            text = "Input your fullname",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                        )
                    },
                    visualTransformation = VisualTransformation.None,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done,

                        ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                )

                SpacerCustom(space = 10)

                // Email
                Text(
                    text = "Email",
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                    )
                )
                SpacerCustom(space = 5)
                OutlinedTextField(value = textEmail,
                    onValueChange = {
                        textEmail = it
                        isEmailValid = Patterns.EMAIL_ADDRESS.matcher(it).matches()
                    },
                    placeholder = {
                        Text(
                            text = "Input your email address",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                        )
                    },
                    visualTransformation = VisualTransformation.None,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done,

                        ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                    leadingIcon = {
                        Icon(
                            Icons.Default.Email,
                            contentDescription = "Email Icon",
                            modifier = Modifier.padding(8.dp),
                            tint = GreenLight
                        )
                    })

                SpacerCustom(space = 10)

                // Phone Number
                Text(
                    text = "Phone Number",
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                    )
                )
                SpacerCustom(space = 5)
                OutlinedTextField(
                    value = textPhoneNumber,
                    onValueChange = {
                        textPhoneNumber = it
                    },
                    placeholder = {
                        Text(
                            text = "Input your phone number",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                        )
                    },
                    visualTransformation = VisualTransformation.None,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done,

                        ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                )

                SpacerCustom(space = 10)

                // Password
                Text(
                    text = "Password",
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                    )
                )
                SpacerCustom(space = 5)
                OutlinedTextField(value = textPassword,
                    onValueChange = {
                        textPassword = it
                    },
                    placeholder = {
                        Text(
                            text = "Input your password",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                        )
                    },
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                    leadingIcon = {
                        Icon(
                            Icons.Default.Lock,
                            contentDescription = "Password Icon",
                            modifier = Modifier.padding(8.dp),
                            tint = GreenLight
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { showPassword = !showPassword }) {
                            val imagePainter = if (showPassword) {
                                painterResource(id = R.drawable.visibility_off)
                            } else {
                                painterResource(id = R.drawable.visibility)
                            }
                            Image(
                                painter = imagePainter,
                                contentDescription = if (showPassword) "Show Password" else "Hide Password",
                            )

                        }
                    })

                SpacerCustom(space = 10)
                // Button Next
                Button(modifier = modifier
                    .fillMaxWidth(), onClick = {
                    if(textFullname.isEmpty() || textEmail.isEmpty() || textPhoneNumber.isEmpty())
                    {
                        sweetAlert(
                            context = context,
                            title = "Warning",
                            contentText = "Please fill all the form",
                            type = "warning", isCancel = true)
                    }
                    else
                    {
                        if(textPhoneNumber.length in 11..13)
                        {
//                            navController.navigate(
//                                route = Screen.RegisterUserScreenPassword.route.replace(
//                                    "{name}", textFullname
//                                ).replace(
//                                    "{email}", textEmail
//                                ).replace(
//                                    "{phone}", textPhoneNumber
//                                )
//                            )
                        }
                        else
                        {
                            sweetAlert(
                                context = context,
                                title = "Warning",
                                contentText = "Phone number at least 11 until max 13 digits",
                                type = "warning", isCancel = true)
                        }
                    }
                }) {
                    Text(
                        text = "Save Changed", style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold, letterSpacing = 0.003.sp
                        )
                    )
                }

            }
            
        }
    }

}

@Preview
@Composable
fun EditProfilePreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        EditProfileScreen()
    }
}