package com.ecotup.ecotupapplication.ui.maps.mapsorder

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.viewModelScope
import com.ecotup.ecotupapplication.BuildConfig
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.cammon.Result
import com.ecotup.ecotupapplication.data.model.Point
import com.ecotup.ecotupapplication.data.vmf.ServiceViewModelFactory
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.databinding.ActivityMapsOrderBinding
import com.ecotup.ecotupapplication.ui.user.home.HomeUserViewModel
import com.ecotup.ecotupapplication.util.IntentToLoadingOrderSuccess
import com.ecotup.ecotupapplication.util.directionhelper.FetchURL
import com.ecotup.ecotupapplication.util.directionhelper.TaskLoadedCallback
import com.ecotup.ecotupapplication.util.getReadableLocation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.roundToInt
import kotlin.random.Random

class MapsOrder : AppCompatActivity(), OnMapReadyCallback, TaskLoadedCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsOrderBinding
    private var currentPolyline: Polyline? = null
    private val viewModel by viewModels<MapsOrderViewModel> {
        ServiceViewModelFactory.getInstance(this@MapsOrder)
    }
    private val viewModel2 by viewModels<MapsOrderViewModel2> {
        ViewModelFactory.getInstance(this@MapsOrder)
    }
    private val homeViewModel by viewModels<HomeUserViewModel> {
        ViewModelFactory.getInstance(this@MapsOrder)
    }
    private lateinit var idDriver: String
    private lateinit var idUser: String
    private lateinit var distance: String
    private var latDriver: Double = 0.0
    private var longDriver: Double = 0.0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        viewModel.viewModelScope.launch {
            viewModel.getDriver().collect { driver ->
                idDriver = driver.idDriver
                idUser = driver.idUser
                latDriver = driver.latitude.toDouble()
                longDriver = driver.longitude.toDouble()
                distance = driver.distance
                Log.d("onCreate", "driver: $latDriver $longDriver")

                binding.tvLocationDriver.text =
                    getReadableLocation(latDriver, longDriver, this@MapsOrder)
                binding.tvDistance.text = if (DoubleToInt(distance) < 1) {
                    "< 1 Km"
                } else {
                    "${DoubleToInt(distance)} Km"
                }
                if (DoubleToInt(distance) < 1) {
                    binding.tvEstimateCost.text = "Rp. 10000"
                } else {
                    binding.tvEstimateCost.text = "Rp. ${DoubleToInt(distance) * 10000}"

                }

            }
        }

        binding.btnOrderNow.setOnClickListener {
            if (binding.etDescription.text.isEmpty()) {
                binding.etDescription.error = "The weight cannot be empty !"
                binding.etDescription.focusable
            } else if (binding.etWeight.text.isEmpty()) {
                binding.etWeight.error = "The weight cannot be empty !"
                binding.etWeight.focusable
            } else {
                try {
                    val weight = binding.etWeight.text.toString()
                    val weight2 = weight.toDouble()
                    Log.d("IDUSER", idUser)
                    runBlocking {
                        viewModel2.insertTransaksi(
                            driver_id = idDriver,
                            user_id = idUser,
                            description = binding.etDescription.text.toString(),
                            total_payment = if (DoubleToInt(distance) < 1) 10000.0 else DoubleToInt(
                                distance
                            ) * 10000.0,
                            total_weight = weight2,
                            total_point = Random.nextInt(10, 501),
                            status = "On Going"
                        ).observe(this@MapsOrder)
                        { result ->
                            if (result != null) {
                                when (result) {
                                    is Result.Loading -> {
                                        // Loading
                                    }

                                    is Result.Success -> {
                                        IntentToLoadingOrderSuccess(this@MapsOrder)
                                    }

                                    is Result.Error -> {
                                        // Error
                                    }
                                }
                            }
                        }
                    }
                } catch (e: NumberFormatException) {
                    binding.etWeight.error = "Incorrect weight input"
                    binding.etWeight.focusable
                }
            }
        }
    }

    private fun DoubleToInt(distance: String): Int {
        val distanceDouble = distance.toDouble()
        val doubleToInt = distanceDouble.roundToInt()
        return doubleToInt
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var latUser: Double = 0.0
        var longUser: Double = 0.0

        homeViewModel.getDetailUser(idUser).observe(this@MapsOrder)
        { result ->
            if (result != null) {
                when (result) {
                    is Result.Success -> {
                        latUser = result.data.data?.userLatitude as Double
                        longUser = result.data.data?.userLongitude as Double
                        binding.tvLocationUser.text =
                            getReadableLocation(latUser, longUser, this@MapsOrder)

                        Log.d("onCreate", "user: $latUser $longUser")

                        val markerInfo = listOf(
                            Point(
                                LatLng(latUser, longUser),
                                "Your Location",
                                getReadableLocation(latUser, longUser, this@MapsOrder),
                                R.drawable.form_point,
                                Color.parseColor("#2CBE21")
                            ),

                            Point(
                                LatLng(latDriver, longDriver),
                                "Driver Location",
                                getReadableLocation(latDriver, longDriver, this@MapsOrder),
                                R.drawable.to_point,
                                Color.parseColor("#298AD8")
                            )

                        )

                        // for each semua titik
                        markerInfo.forEach {
                            mMap.addMarker(
                                MarkerOptions()
                                    .position(it.position)
                                    .title(it.title)
                                    .snippet(it.snippet)
                                    .icon(vectorToBitmap(this, it.iconResId, it.color))
                            )
                        }


                        FetchURL(this).execute(
                            getUrl(
                                LatLng(latDriver, longDriver),
                                LatLng(latUser, longUser),
                                "driving"
                            ), "driving"
                        )

                        // Mendapatkan semua maker ketika run
                        val builder = LatLngBounds.Builder()
                        markerInfo.forEach { markerInfo2 ->
                            builder.include(markerInfo2.position)
                        }
                        val bounds = builder.build()


                        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 250))
                    }

                    else -> {}
                }
            }
        }
    }

    private fun getUrl(origin: LatLng, dest: LatLng, directionMode: String): String {
        val origin = "origin=" + origin.latitude + "," + origin.longitude
        val dest = "destination=" + dest.latitude + "," + dest.longitude
        val mode = "mode=$directionMode"
        val parameters = "$origin&$dest&$mode"
        val output = "json"
        val url =
            "https://maps.googleapis.com/maps/api/directions/$output?$parameters&key=${BuildConfig.API_MAPS_KEY}"
        return url
    }


    override fun onTaskDone(vararg values: Any?) {
        if (currentPolyline != null)
            currentPolyline!!.remove()
        currentPolyline = mMap.addPolyline(values[0] as PolylineOptions)
    }

    private fun vectorToBitmap(
        context: Context, @DrawableRes id: Int, @ColorInt color: Int
    ): BitmapDescriptor {
        val vectorDrawable = ResourcesCompat.getDrawable(context.resources, id, null)
        if (vectorDrawable == null) {
            Log.e("BitmapHelper", "Resource not found")
            return BitmapDescriptorFactory.defaultMarker()
        }
        val size = 65
        val bitmap = Bitmap.createBitmap(
            size, size, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, size, size)
        DrawableCompat.setTint(vectorDrawable, color)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}


