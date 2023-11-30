package com.ecotup.ecotupapplication.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import cn.pedant.SweetAlert.SweetAlertDialog
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

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


fun sweetAlert(context: Context, title : String, contentText: String, type : String, isCancel : Boolean = false)
{
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
