package com.ecotup.ecotupapplication.ui.maps

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.beust.klaxon.Parser
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.theme.BlueLight
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.DropDownVehicle
import com.ecotup.ecotupapplication.util.SpacerCustom
import com.ecotup.ecotupapplication.util.getCurrentLocation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.ktx.model.cameraPosition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.URL

@Composable
fun MapsScreen(modifier: Modifier = Modifier, navController: NavController, context: Context) {
    var showMap by remember { mutableStateOf(false) }
    var location by remember { mutableStateOf(LatLng(0.0, 0.0)) }
    var mapProperties by remember { mutableStateOf(MapProperties()) }

    // MDP A : -2.973418951270049, 104.76402610223212
    // MDP B : -2.961426848289628, 104.73948240419026

    getCurrentLocation(context) {
        location = it
        showMap = true
    }

    if (showMap) {
        MapsEcotup(context = context,
            latLng = location,
            mapProperties = mapProperties,
            onChangeMapType = { mapProperties = mapProperties.copy(mapType = it) })
    } else {
        Text(text = "Loading Maps Ecotup...")
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun MapsEcotup(
    context: Context,
    latLng: LatLng,
    mapProperties: MapProperties = MapProperties(),
    onChangeMapType: (mapType: MapType) -> Unit,
    modifier: Modifier = Modifier
) {
    val latLongList = remember {
        mutableStateListOf(latLng)
    }
    var cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLng, 10f)
    }

    latLongList.addAll(
        listOf(
            LatLng(-2.973418951270049, 104.76402610223212), // MDP A
            LatLng(-2.961426848289628, 104.73948240419026)  // MDP B
            // Add more points as needed
        )
    )


    Box(modifier = modifier.fillMaxSize()) {
        GoogleMap(
            modifier = modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
        ) {
            latLongList.toList().forEach {

                Marker(
                    state = MarkerState(position = it),
                    title = "Location",
                    snippet = "Marker in current location",
                )
            }

            val LatLongB = LatLngBounds.Builder()


            val options = PolylineOptions()
                options.color(0xff0000ff.toInt())
                options.width(5f)

            val url = PolylineManager.getURL(latLongList.first(), latLongList.last())
            runBlocking {
                // Connect to URL, download content and convert into string asynchronously
                var result = ""
                launch(Dispatchers.Default) {
                    result = URL(url).readText()
                }
                // When API call is done, create parser and convert into JsonObjec
                val parser: Parser = Parser()
                val stringBuilder: StringBuilder = StringBuilder(result)
                val json: JsonObject = parser.parse(stringBuilder) as JsonObject
                // get to the correct element in JsonObject
                val routes = json.getAsJsonArray("routes")
//                val points = routes!!["legs"]["steps"][0] as JsonArray<JsonObject>
                val points = routes[0].asJsonObject["legs"].asJsonArray[0].asJsonObject["steps"].asJsonArray
                // For every element in the JsonArray, decode the polyline string and pass all points to a List
                val polypts = points.flatMap {
                    PolylineManager.decodePoly(it.asJsonObject["polyline"].asJsonObject["points"].asString)
                }
                // Add  points to polyline and bounds
                options.add(latLongList.first())

                LatLongB.include(latLongList.first())
                for (point in polypts) {
                    options.add(point)
                    LatLongB.include(point)
                }
                options.add(latLongList.last())
                LatLongB.include(latLongList.last())
                // build bounds
                val bounds = LatLongB.build()
                // add polyline to the map
                PolylineOptions().addAll(options.points)
                // show map with route centered

                cameraPosition {
                    target(bounds.center)
                    zoom(100f)
                }
            }
        }

        Column(
            modifier = modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            MapsAddress()
        }

        Column(
            modifier = modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.White, RoundedCornerShape(10.dp))
        ) {
            MapsOrder(modifier = modifier.fillMaxWidth())
        }


    }
}

@Composable
fun MapsAddress(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(10.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Image
            Image(
                painter = painterResource(id = R.drawable.form_point),
                contentDescription = "from_point",
                modifier = Modifier.size(width = 25.dp, height = 25.dp)
            )

            SpacerCustom(space = 5)
            // Column  : From + Rumah Ecotup
            Column(modifier = modifier.fillMaxWidth()) {
                Text(
                    text = "From", style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Gray,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.003.sp
                    )
                )
                SpacerCustom(space = 2)
                Text(
                    text = "Rumah Ecotup", style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.003.sp
                    )
                )
            }
        }
        SpacerCustom(space = 5)
        Image(
            painter = painterResource(id = R.drawable.lines),
            contentDescription = "lines",
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        )
        SpacerCustom(space = 5)

        Row(verticalAlignment = Alignment.CenterVertically) {
            // Image
            Image(
                painter = painterResource(id = R.drawable.to_point),
                contentDescription = "from_point",
                modifier = Modifier.size(width = 25.dp, height = 25.dp)
            )

            SpacerCustom(space = 5)

            // Column  : To + Rumah Ecotup
            Column(modifier = modifier.fillMaxWidth()) {
                Text(
                    text = "To", style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Gray, fontSize = 15.sp, fontWeight = FontWeight.Bold
                    )
                )
                SpacerCustom(space = 2)
                Text(
                    text = "Tempat Pengolahan Akhir",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = BlueLight, fontSize = 15.sp, fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Composable
fun MapsOrder(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        SpacerCustom(space = 5)
        // ONE TIME ORDER
        Column(modifier = modifier.fillMaxWidth()) {
            Text(
                text = "One Time Order", style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Black,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                ), modifier = modifier.align(Alignment.CenterHorizontally)
            )
        }

        SpacerCustom(space = 10)

        // ROW = KANAN + KIRI
        // KIRI = Vehicle + Spiner
        // KANAN = Image
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // KIRI
            Column {
                // Vehicle
                Text(
                    text = "Vehicle", style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontSize = 15.sp,
                    )
                )
                SpacerCustom(space = 5)
                // DropDown
                DropDownVehicle()

            }
            // KANAN
            Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.truck_waste),
                    contentDescription = "vehicle",
                    modifier = modifier.size(width = 69.dp, height = 41.dp)
                )
            }

        }
        SpacerCustom(space = 10)

        // ROW : Waste + Price
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Waste
            Text(
                text = "Waste transportation costs",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = GreenLight,
                    fontSize = 15.sp,
                )
            )
            // Price
            Text(
                text = "Rp. 14.000", style = MaterialTheme.typography.bodyMedium.copy(
                    color = GreenLight, fontSize = 20.sp, fontWeight = FontWeight.Bold
                )
            )
        }

        SpacerCustom(space = 5)

        Button(onClick = { /*TODO*/ }, modifier = modifier.fillMaxWidth()) {
            Text(
                text = "Order Now", style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    fontSize = 15.sp,
                )
            )
        }
        SpacerCustom(space = 5)
    }
}
