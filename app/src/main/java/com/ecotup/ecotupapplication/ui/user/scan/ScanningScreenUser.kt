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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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


    var buttonCheckImage by remember {
        mutableStateOf(true)
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
                modifier = modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .background(
                        GreenLight,
                        RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                    )
                    .align(Alignment.TopCenter)
            )
            {

            }
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
                                color = Color.White,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.003.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                        val painterBack = painterResource(id = R.drawable.back_white)
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

                            Box(
                                modifier = modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            )
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
                                if (buttonCheckImage) {
                                    Button(
                                        onClick = {
                                            val imageUri: Uri = capturedImageUri
                                            val bitmap =
                                                MediaStore.Images.Media.getBitmap(
                                                    context.contentResolver,
                                                    imageUri
                                                )
                                            typeTrash = classifyImage(context, bitmap)
                                            typeTrash2 = classifySoftMax(context, bitmap)
                                            isCheckImage = true
                                            buttonCheckImage = false
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
                                } else {
                                    Button(
                                        onClick = {
                                            buttonCheckImage = true
                                            isCheckImage = false
                                            isTakeImage = true
                                        }, modifier = modifier
                                            .width(150.dp)
                                            .align(Alignment.CenterHorizontally)
                                    ) {
                                        Text(
                                            text = "Capture Again",
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                color = Color.White,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Bold,
                                                letterSpacing = 0.003.sp
                                            )
                                        )
                                    }
                                }


                            }
                            if (isCheckImage) {
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
                                Column(
                                    modifier = modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                )
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
                                Column(
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                )
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
                                        text = when (typeTrash) {
                                            "Organik" -> organikDesc
                                            "Anorganik" -> anorganikDesc
                                            else -> "Unknown"
                                        },
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = GreenLight,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            letterSpacing = 0.003.sp,
                                            textAlign = TextAlign.Justify
                                        )
                                    )

                                    if (typeTrash2 == "Anorganik") {
                                        Text(
                                            text = when (typeTrash2) {
                                                "Glass" -> glassDesc
                                                "Metal" -> metalDesc
                                                "Paper" -> paperDesc
                                                "Plastic" -> plasticDesc
                                                else -> "Unknown"
                                            },
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                color = GreenLight,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Bold,
                                                letterSpacing = 0.003.sp,
                                                textAlign = TextAlign.Justify
                                            )
                                        )
                                    }
                                }

                                SpacerCustom(space = 10)
                                Text(
                                    text = "Did you know ?",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = Color.Black,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        letterSpacing = 0.003.sp
                                    ),
                                    modifier = modifier.padding(16.dp)
                                )
                                SpacerCustom(space = 5)
                            }
                        }

                        if (typeTrash == "Organik") {
                            items(organic) {
                                ListFunFact(text = it)
                            }
                        } else if (typeTrash == "Anorganik") {
                            when (typeTrash2) {
                                "Glass" -> items(glass) {
                                    ListFunFact(text = it)
                                }

                                "Metal" -> items(metal) {
                                    ListFunFact(text = it)
                                }

                                "Paper" -> items(paper) {
                                    ListFunFact(text = it)
                                }

                                "Plastic" -> items(plastics) {
                                    ListFunFact(text = it)
                                }

                                else -> "Unknown"
                            }
                        }

                        item {
                            if (isCheckImage) {
                                Column(
                                    modifier = modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Button(onClick = {
                                        runBlocking {
                                            viewModel.getSessionUser()
                                                .observe(context as LifecycleOwner)
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
                                        Text(
                                            text = "Claim Point",
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                color = Color.White,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Bold,
                                                letterSpacing = 0.003.sp
                                            )
                                        )
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
            .border(1.dp, Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
            .background(Color.White, RoundedCornerShape(16.dp))
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

@Composable
fun ListFunFact(text: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .shadow(4.dp, shape = MaterialTheme.shapes.medium),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.approval),
                modifier = modifier.size(20.dp),
                contentDescription = "approval"
            )
            Spacer(modifier = modifier.padding(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Justify
                ),
                modifier = modifier.fillMaxWidth()
            )
        }

    }
}

val metalDesc =
    "Metal is a material that has a strong structure, hardness and good electrical conductivity. Usually metal garbage is produced by industry, vehicles, construction and electronics. It is very easy to start with the reprocessing and then clean up the metal so that it is then cut to the melting of the material which will eventually be formed to produce a new shape as needed."

val metal = arrayOf(
    "In ancient times metal was used from agricultural tools to tools.",
    "The recycling of metal garbage takes less energy than the processing of new metal seeds.",
    "Metal garbage is a fairly recyclable waste of up to 95%. Every year, about 1.7 million tons of metal waste are recycled in Indonesia",
    "Greenhouse gas emissions can be reduced by up to 75% from recycling metal garbage"
)

val glassDesc =
    "Glass is a solid material made of silica or quartz sand, although glass is fragile and easily broken, usually glass garbage is produced from residues of industrial, construction, and household items. For its own recycling, the glass garbage is cleaned and dissolved in such a way that it becomes small pieces and then spilled at high temperatures until it can eventually be converted into a new item."

val glass = arrayOf(
    "Glass garbage is quite recyclable, up to 95 percent",
    "Each tonne of recycled glass garbages can reduce CO2 emissions by about 315 kilograms",
    "Glass recycling saves up to 50% of energy compared to new glass production"
)

val plasticDesc =
    "Plastics are materials that have lightweight, easy to form and durable properties. These materials are made of polymers, which are small units that form large molecules. Usually plastic garbage is produced by residues of industrial, construction, and household items. For re-processing, the plastic garbage will be cleaned and washed and then destroyed into plastic pieces that will then be processed into new forms"

val plastics = arrayOf(
    "Plastic was first discovered by Alexander Parkes in 1862",
    "Plastic was originally created to be reusable, but the overuse of plastic today makes it one of the core factors of environmental pollution",
    "Plastic garbage is a hard-to-decompose material that will eventually accumulate and pollute the environment.",
    "About 8 million tons of plastic trash a year goes into the ocean, which is the analogy of throwing trash trucks into the sea every minute."
)

val paperDesc =
    "Paper is a material that has the properties resulting from the fiber comparison of the pulp (sources of wood, bamboo and paper waste). Usually paper garbage is produced from a variety of sources, such as households, factories, and industrial environments."

val paper = arrayOf(
    "For its own recycling, the paper garbage is collected and crushed into a pulp where the pulp will then be re-formed into recycled paper",
    "Paper was first discovered in China during the Han Dynasty around 105 BC by a palace official named Cai Lun",
    "Each ton of recycled paper garbage can reduce CO2 emissions by about 900 kilograms"
)

val organikDesc =
    "Organic refers to anything that comes from or is related to living organisms, such as agriculture, food, and other products, that are produced naturally without the use of synthetic chemicals. The organic concept embraces the principles of sustainability, emphasizing the use of natural fertilizers, sustainable farming techniques, and the avoidance of chemical pesticides. Organic food is produced without chemical fertilizers or synthetic additives, and is considered healthier because it does not contain chemical residues. Other organic products, such as clothing and cleaning products, are made from natural ingredients without harmful chemicals. Organic certification indicates that a product meets organic standards set by a certification authority."
val anorganikDesc =
    "Anorganic refers to substances or compounds that lack carbon and are not derived from living organisms. These materials typically include minerals, metals, and non-living matter. Inorganic compounds play a vital role in various chemical processes, such as mineral formations and industrial applications. Unlike organic compounds, which contain carbon-hydrogen bonds and are prevalent in living organisms, anorganic substances are often associated with geological processes and non-biological origins. Understanding the distinction between organic and inorganic substances is fundamental in chemistry and science, contributing to advancements in fields ranging from materials science to environmental studies."

val organic = arrayOf(
    "Organic waste can be processed into rich natural fertilizers.",
    "The composting process of organic waste generates energy and reduces waste.",
    "Recycling organic waste reduces greenhouse gas emissions.",
    "Compost from organic waste enhances soil health and agriculture.",
    "Separating organic waste lessens the burden on landfills.",
    "Organic kitchen waste compost improves organic farming productivity.",
    "The nutrient composition of organic waste supports natural ecosystem cycles.",
    "Processing organic waste can yield biogas as an energy source.",
    "Thoughtful organic waste management promotes environmental sustainability.",
    "Sorting organic waste is a crucial step toward a cleaner environment."
)