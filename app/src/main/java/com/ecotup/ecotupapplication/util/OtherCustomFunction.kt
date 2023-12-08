package com.ecotup.ecotupapplication.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import cn.pedant.SweetAlert.SweetAlertDialog
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ecotup.ecotupapplication.MainActivity

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
