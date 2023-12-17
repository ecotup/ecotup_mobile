package com.ecotup.ecotupapplication.ui.user.scan

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ecotup.ecotupapplication.BuildConfig
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.cammon.Result
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.ml.TrashclassificationSigmoid
import com.ecotup.ecotupapplication.ml.TrashclassificationSoftmax
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenDark
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.ClickableImageBack
import com.ecotup.ecotupapplication.util.SpacerCustom
import com.ecotup.ecotupapplication.util.sweetAlert
import kotlinx.coroutines.runBlocking
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects
import kotlin.random.Random

@Composable
fun ScanningScreenUser(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ScanningUserViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            LocalContext.current
        )
    )
) {
    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        BuildConfig.APPLICATION_ID + ".provider", file
    )

    var typeTrash by remember {
        mutableStateOf("Indentify...")
    }

    var typeTrash2 by remember {
        mutableStateOf("Indentify...")
    }

    var isTakeImage by remember {
        mutableStateOf(true)
    }

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    var point by remember {
        mutableIntStateOf(0)
    }

    var idUser by remember {
        mutableStateOf("")
    }

    var isCheckImage by remember {
        mutableStateOf(false)
    }

    var isIdentify by remember {
        mutableStateOf(false)
    }

    val randomValue = remember { Random.nextInt(10, 501) }

    if (isTakeImage == true) {
        val cameraLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
                capturedImageUri = uri
                isTakeImage = false
            }

        val permissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            if (it) {
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
                cameraLauncher.launch(uri)
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }

        Box(modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .dashedBorder(2.dp, 16.dp, Color.Gray)
            .clickable {
                val permissionCheckResult =
                    ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                    cameraLauncher.launch(uri)
                } else {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            })
        {
            Image(
                painter = painterResource(id = R.drawable.background_doodle),
                contentDescription = "background_doodle",
                modifier = modifier.fillMaxSize()
            )
            Column(
                modifier = modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.camera_scan),
                    contentDescription = "camera_scan",
                    modifier = modifier.size(100.dp),
                    colorFilter = ColorFilter.tint(Color.Gray)
                )
                SpacerCustom(space = 10)
                Text(
                    text = "Tap here to start identifying waste types",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Gray,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.003.sp
                    )
                )
            }
        }
    } else {
        // Result Scanning
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center)
        {
            Image(
                painter = painterResource(id = R.drawable.background_doodle),
                contentDescription = "background_doodle",
                modifier = modifier.fillMaxSize()
            )
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                if (capturedImageUri.path?.isNotEmpty() == true) {
                    SpacerCustom(space = 5)

                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp), contentAlignment = Alignment.Center
                    )
                    {
                        Text(
                            text = "Image Classification",
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.Black,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.003.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                        val painterBack = painterResource(id = R.drawable.button_back)
                        Column(modifier = modifier.align(Alignment.CenterStart)) {
                            ClickableImageBack(
                                painter = painterBack,
                                contentDescription = "Back",
                                onClick = { navController.popBackStack() },
                                40,
                                40
                            )
                        }
                    }
                    LazyColumn(contentPadding = PaddingValues(bottom = 12.dp))
                    {
                        item {
                            SpacerCustom(space = 10)

                            Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
                            {
                                Image(
                                    modifier = modifier
                                        .size(250.dp),
                                    painter = rememberAsyncImagePainter(capturedImageUri),
                                    contentDescription = null
                                )
                            }

                            SpacerCustom(space = 10)

                            Column(
                                modifier = modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Button(
                                    onClick = {
                                        val imageUri: Uri = capturedImageUri
                                        val bitmap =
                                            MediaStore.Images.Media.getBitmap(
                                                context.contentResolver,
                                                imageUri
                                            )
                                        isIdentify = true
                                        typeTrash = classifyImage(context, bitmap)
                                        typeTrash2 = classifySoftMax(context, bitmap)
                                        isIdentify = false
                                        isCheckImage = true
                                    }, modifier = modifier
                                        .width(150.dp)
                                        .align(Alignment.CenterHorizontally)
                                ) {
                                    Text(
                                        text = "Check Image",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = Color.White,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            letterSpacing = 0.003.sp
                                        )
                                    )
                                }
                            }

                            if(isIdentify)
                            {
                                Column(modifier = modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                                    Text(
                                        text = "Identify...",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = Color.Black,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            letterSpacing = 0.003.sp
                                        )
                                    )
                                }

                                // Kasih Loading
                            }

                            if(isCheckImage)
                            {
                                SpacerCustom(space = 10)
                                Row(
                                    modifier = modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceAround
                                )
                                {
                                    classifyText(
                                        modifier,
                                        typeTrash,
                                        if (typeTrash == "Organik") GreenLight else Color.Yellow
                                    )

                                    if (typeTrash == "Anorganik") {
                                        classifyText(
                                            modifier, typeTrash2, when (typeTrash2) {
                                                "Glass" -> Color.Gray
                                                "Metal" -> Color.Black
                                                "Paper" -> GreenDark
                                                "Plastic" -> Color.Yellow
                                                else -> Color.White
                                            }
                                        )
                                    }

                                }

                                SpacerCustom(space = 10)

                                // point ->
                                Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally)
                                {
                                    Text(
                                        text = "You get $randomValue point",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = Color.Gray,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            letterSpacing = 0.003.sp
                                        )
                                    )

                                }

                                // Deskripsi
                                SpacerCustom(space = 5)
                                Column(modifier = modifier
                                    .fillMaxWidth()
                                    .padding(16.dp))
                                {
                                    Text(
                                        text = "Description",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = Color.Black,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            letterSpacing = 0.003.sp
                                        )
                                    )
                                    SpacerCustom(space = 5)
                                    Text(
                                        text = "Lorem ipsum dolor sit amet, cons Lorem ipsum dolor sit amet, cons Lorem ipsum dolor sit amet, cons Lorem ipsum dolor sit amet, cons Lorem ipsum dolor sit amet, cons Lorem ipsum dolor sit amet, cons Lorem ipsum dolor sit amet, cons Lorem ipsum dolor sit amet, cons Lorem ipsum dolor sit amet, cons Lorem ipsum dolor sit amet, cons Lorem ipsum dolor sit amet, cons Lorem ipsum dolor sit amet, cons Lorem ipsum dolor sit amet, cons Lorem ipsum dolor sit amet, cons Lorem ipsum dolor sit amet, cons Lorem ipsum dolor sit amet, cons Lorem ipsum dolor sit amet, cons",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = GreenLight,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            letterSpacing = 0.003.sp,
                                            textAlign = TextAlign.Justify
                                        )
                                    )
                                }

                                // Fakta Menarik dalam bentuk point
                                SpacerCustom(space = 10)

                                Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                                    Button(onClick = {
                                        runBlocking {
                                            viewModel.getSessionUser().observe(context as LifecycleOwner)
                                            {
                                                idUser = it.id
                                                point = it.point.toInt()
                                            }

                                            viewModel.updatePoint(idUser, (point + randomValue))
                                                .observe(context as LifecycleOwner)
                                                { result ->
                                                    if (result != null) {
                                                        when (result) {
                                                            is Result.Loading -> {}
                                                            is Result.Success -> {
                                                                sweetAlert(
                                                                    context = context,
                                                                    "Success Claim Point",
                                                                    "You get $randomValue point",
                                                                    "success",
                                                                    false
                                                                )
                                                                isTakeImage = true
                                                                navController.navigate(Screen.HomeScreenUser.route)
                                                            }

                                                            is Result.Error -> {}
                                                            else -> {

                                                            }
                                                        }
                                                    }
                                                }
                                        }
                                    }) {
                                        Text(text = "Claim Point")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    return File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
}

fun Modifier.dashedBorder(width: Dp, radius: Dp, color: Color) =
    drawBehind {
        drawIntoCanvas {
            val paint = Paint()
                .apply {
                    strokeWidth = width.toPx()
                    this.color = color
                    style = PaintingStyle.Stroke
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                }
            it.drawRoundRect(
                width.toPx(),
                width.toPx(),
                size.width - width.toPx(),
                size.height - width.toPx(),
                radius.toPx(),
                radius.toPx(),
                paint
            )
        }
    }

private fun classifyImage(context: Context, image: Bitmap): String {
    val imageSize = 255
    val model = TrashclassificationSigmoid.newInstance(context)

    val scaledBitmap = Bitmap.createScaledBitmap(image, imageSize, imageSize, true)

    val inputFeature0 =
        TensorBuffer.createFixedSize(intArrayOf(1, imageSize, imageSize, 3), DataType.FLOAT32)
    val byteBuffer: ByteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
    byteBuffer.order(ByteOrder.nativeOrder())

    val intValues = IntArray(imageSize * imageSize)
    scaledBitmap.getPixels(
        intValues,
        0,
        scaledBitmap.width,
        0,
        0,
        scaledBitmap.width,
        scaledBitmap.height
    )

    for (pixelValue in intValues) {
        byteBuffer.putFloat(((pixelValue shr 16 and 0xFF) / 255.0).toFloat()) // Red channel
        byteBuffer.putFloat(((pixelValue shr 8 and 0xFF) / 255.0).toFloat())  // Green channel
        byteBuffer.putFloat(((pixelValue and 0xFF) / 255.0).toFloat())         // Blue channel
    }
    inputFeature0.loadBuffer(byteBuffer)

    val outputs = model.process(inputFeature0)
    val outputFeature0 = outputs.outputFeature0AsTensorBuffer

    val organikProbability = outputFeature0.floatArray[0]
    val anorganikProbability =
        outputFeature0.floatArray[1]

    val maxProb = maxOf(organikProbability, anorganikProbability)

    model.close()

    return if (maxProb == organikProbability) {
        "Organik"
    } else if (maxProb == anorganikProbability) {
        "Anorganik"
    } else {
        "Unknown"
    }
}

private fun classifySoftMax(context: Context, image: Bitmap): String {
    val imageSize = 255
    val model = TrashclassificationSoftmax.newInstance(context)

    val scaledBitmap = Bitmap.createScaledBitmap(image, imageSize, imageSize, true)

    val inputFeature0 =
        TensorBuffer.createFixedSize(intArrayOf(1, imageSize, imageSize, 3), DataType.FLOAT32)
    val byteBuffer: ByteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
    byteBuffer.order(ByteOrder.nativeOrder())

    val intValues = IntArray(imageSize * imageSize)
    scaledBitmap.getPixels(
        intValues,
        0,
        scaledBitmap.width,
        0,
        0,
        scaledBitmap.width,
        scaledBitmap.height
    )

    for (pixelValue in intValues) {
        byteBuffer.putFloat(((pixelValue shr 16 and 0xFF) / 255.0).toFloat()) // Red channel
        byteBuffer.putFloat(((pixelValue shr 8 and 0xFF) / 255.0).toFloat())  // Green channel
        byteBuffer.putFloat(((pixelValue and 0xFF) / 255.0).toFloat())         // Blue channel
    }

    inputFeature0.loadBuffer(byteBuffer)

    val outputs = model.process(inputFeature0)
    val outputFeature0 = outputs.outputFeature0AsTensorBuffer

    val output = outputFeature0.floatArray[0]
    val output1 = outputFeature0.floatArray[1]
    val output2 = outputFeature0.floatArray[2]
    val output3 = outputFeature0.floatArray[3]
    val maxValue = maxOf(output, output1, output2, output3)

    Log.d("Max ", maxValue.toString())

    model.close()

    return if (output == maxValue) {
        "Glass"
    } else if (output1 == maxValue) {
        "Metal"
    } else if (output2 == maxValue) {
        "Paper"
    } else if (output3 == maxValue) {
        "Plastic"
    } else {
        "Unknown"
    }
}

@Composable
private fun classifyText(modifier: Modifier = Modifier, text: String, color: Color) {
    Row(
        modifier = modifier
            .width(150.dp)
            .height(50.dp)
            .shadow(2.dp, RoundedCornerShape(10.dp))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Column(
            modifier = modifier
                .size(10.dp)
                .background(color = color, shape = CircleShape)
        ) { }
        Text(
            text = text, style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.003.sp
            )
        )

    }
}
