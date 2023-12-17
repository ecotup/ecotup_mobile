package com.ecotup.ecotupapplication.util

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.location.Geocoder
import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import cn.pedant.SweetAlert.SweetAlertDialog
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ecotup.ecotupapplication.MainActivity
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun getDrawableFromResource(context: Context, drawableId: Int): Drawable? {
    return ContextCompat.getDrawable(context, drawableId)
}

@Composable
fun getRememberAsyncImagePainter(context: Context, data: Drawable?) = rememberAsyncImagePainter(
    ImageRequest.Builder(context).data(data = data).apply {
        crossfade(true)
    }.build()
)

@Composable
fun SpacerCustom(space: Int, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.padding(space.dp))
}


fun sweetAlert(
    context: Context,
    title: String,
    contentText: String,
    type: String,
    isCancel: Boolean = false
) {
    when (type) {
        "error" -> {
            val sweetAlertDialog =
                SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(title)
                    .setContentText(contentText)
                    .setConfirmButton("OK") {
                        // ARAHKAN KE PAGE LAIN
                        it.dismissWithAnimation()
                    }
            sweetAlertDialog.setCancelable(isCancel)
            sweetAlertDialog.show()
        }

        "success" -> {
            val sweetAlertDialog =
                SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText(title)
                    .setContentText(contentText)
                    .setConfirmButton("OK") {
                        // ARAHKAN KE PAGE LAIN
                        it.dismissWithAnimation()
                    }
            sweetAlertDialog.setCancelable(isCancel)
            sweetAlertDialog.show()
        }

        "warning" -> {
            val sweetAlertDialog =
                SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(title)
                    .setContentText(contentText)
                    .setConfirmButton("OK") {
                        // ARAHKAN KE PAGE LAIN
                        it.dismissWithAnimation()
                    }
            sweetAlertDialog.setCancelable(isCancel)
            sweetAlertDialog.show()
        }
    }
}


@Composable
fun ClickableImageBack(
    painter: Painter,
    contentDescription: String?,
    onClick: () -> Unit,
    w: Int, h: Int,
    modifier: Modifier = Modifier
) {
    Image(painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .size(width = w.dp, height = h.dp)
            .clickable { onClick() }
            .padding(8.dp)
    )
}

fun IntentToMain(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
}



fun checkForPermission(context: Context): Boolean {
    return !(ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) != PackageManager.PERMISSION_GRANTED)
}



@SuppressLint("MissingPermission")
fun getCurrentLocation(context: Context, onLocationFetched: (location: LatLng) -> Unit) {
    var loc: LatLng
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    fusedLocationClient.lastLocation
        .addOnSuccessListener { location: Location? ->
            if (location != null) {
                val latitude = location.latitude
                val longitude = location.longitude
                loc = LatLng(latitude,longitude)
                onLocationFetched(loc)
            }
        }
        .addOnFailureListener { exception: Exception ->
            // Handle failure to get location
            Log.d("MAP-EXCEPTION",exception.message.toString())
        }

}

fun getReadableLocation(latitude: Double, longitude: Double, context: Context): String {
    var addressText = "Not Found"
    val geocoder = Geocoder(context, Locale.getDefault())

    try {
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)

        if (addresses?.isNotEmpty() == true) {
            val address = addresses[0]
//            addressText = "${address.getAddressLine(0)}, ${address.locality}"
            addressText = "${address.subLocality}, ${address.locality}, ${address.subAdminArea}, ${address.adminArea}"
            Log.d("geolocation", addressText)
        }

    } catch (e: IOException) {
        Log.d("geolocation", e.message.toString())

    }

    return addressText

}

fun convertTimestampToDate(timestamp: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")

    val outputFormat = SimpleDateFormat("dd MMMM yyyy")
    outputFormat.timeZone = TimeZone.getDefault()

    try {
        val date = inputFormat.parse(timestamp)
        return outputFormat.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return ""
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownVehicle() {
    val context = LocalContext.current
    val vehicle = arrayOf("Car", "Motorcycle", "Pedicab", "Trike Motorbike")
    var expanded by remember { mutableStateOf(false) }
    var selectedItemIndex by remember { mutableIntStateOf(0) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier,
    ) {
        OutlinedTextField(
            value = vehicle[selectedItemIndex],
            onValueChange = { },
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .height(50.dp)
                .width(200.dp),
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            vehicle.forEachIndexed { index, item ->
                DropdownMenuItem(text = {
                    Text(
                        text = item,
                        fontWeight = if (index == selectedItemIndex) FontWeight.Bold else null
                    )
                }, onClick = {
                    selectedItemIndex = index
                    expanded = false
                    Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}

@Composable
fun ButtonGoogle(
    modifier: Modifier = Modifier, image: Int, text: String, click: () -> Unit
) {
    Column(modifier = modifier
        .shadow(2.dp, RoundedCornerShape(10.dp), ambientColor = Color.Gray)
        .clickable { click() }
        .padding(16.dp)
        .width(100.dp)
    ) {
        Row(modifier = modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround){
            // IMage
            Image(
                painter = painterResource(id = image),
                contentDescription = "google",
                modifier = Modifier.size(18.dp)
            )


            // Text
            Text(
                modifier = modifier,
                text = text,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
        }
    }
}