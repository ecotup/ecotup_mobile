package com.ecotup.ecotupapplication.ui.maps.mapsrunning

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
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
import com.ecotup.ecotupapplication.databinding.ActivityMapsRunningUserBinding
import com.ecotup.ecotupapplication.ui.general.login.LoginViewModel
import com.ecotup.ecotupapplication.ui.user.home.HomeUserViewModel
import com.ecotup.ecotupapplication.util.IntentToFinishTransaction
import com.ecotup.ecotupapplication.util.directionhelper.FetchURL
import com.ecotup.ecotupapplication.util.directionhelper.TaskLoadedCallback
import com.ecotup.ecotupapplication.util.getFoto
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


class MapsRunningUser : AppCompatActivity(), OnMapReadyCallback, TaskLoadedCallback {
    private lateinit var mMap: GoogleMap
    private var currentPolyline: Polyline? = null
    private lateinit var binding: ActivityMapsRunningUserBinding
    private val viewModel by viewModels<MapsRunningViewModel> {
        ServiceViewModelFactory.getInstance(this@MapsRunningUser)
    }
    private val viewModel2 by viewModels<MapsRunningViewModel2> {
        ViewModelFactory.getInstance(this@MapsRunningUser)
    }
    private val homeViewModel by viewModels<HomeUserViewModel> {
        ViewModelFactory.getInstance(this@MapsRunningUser)
    }
    private val loginViewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this@MapsRunningUser)
    }
    private lateinit var idDriver: String
    private lateinit var idUser: String
    private lateinit var distance: String
    private var latDriver: Double = 0.0
    private var longDriver: Double = 0.0
    private var imageDriver = ""
    private var nameDriver = ""
    private var phoneDriver = ""
    private var plateDriver = ""
    private var typeVehicleDriver = ""
    private var idTransaction = ""
    private var isSuccessGet = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsRunningUserBinding.inflate(layoutInflater)
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


                binding.tvLocationDriver.text =
                    getReadableLocation(latDriver, longDriver, this@MapsRunningUser)
            }
        }
    }

    private fun dataDriver() {
        loginViewModel.getDetailDriver(idDriver).observe(this)
        {
            if (it != null) {
                when (it) {
                    is Result.Success -> {
                        imageDriver = it.data.data?.driverProfile.toString()
                        nameDriver = it.data.data?.driverName.toString()
                        phoneDriver = it.data.data?.driverPhone.toString()
                        plateDriver = it.data.data?.driverLicense.toString()
                        typeVehicleDriver = it.data.data?.driverType.toString()

                        binding.tvNameDriver.text = nameDriver
                        binding.tvPlatDriver.text = plateDriver
                        binding.tvTypeDriver.text = typeVehicleDriver
                        getFoto(this, imageDriver, binding.ivProfileDriver)
                        isSuccessGet = true
                    }

                    else -> {}
                }
            }
        }



        binding.llCallDriver.setOnClickListener {
            val url = "https://api.whatsapp.com/send?phone=${phoneDriver}"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var latUser: Double = 0.0
        var longUser: Double = 0.0

        dataDriver()

        homeViewModel.getDetailUser(idUser).observe(this@MapsRunningUser) { result ->
            if (result != null) {
                when (result) {
                    is Result.Success -> {
                        latUser = result.data.data?.userLatitude as Double
                        longUser = result.data.data?.userLongitude as Double
                        binding.tvLocationUser.text =
                            getReadableLocation(latUser, longUser, this@MapsRunningUser)

                        Log.d("onCreate", "user: $latUser $longUser")

                        val handler = Handler(Looper.getMainLooper())
                        val getDataRunnable = object : Runnable {
                            override fun run() {
                                runBlocking {
                                    viewModel2.getLatLongDriver(idUser, idDriver)
                                        .observe(this@MapsRunningUser) { result ->
                                            if (result != null) {
                                                when (result) {
                                                    is Result.Loading -> {
                                                    }

                                                    is Result.Success -> {

                                                        val data = result.data.data
                                                        val latitude =
                                                            data?.transactionLatitudeStart as Double
                                                        val longitude =
                                                            data?.transactionLongitudeStart as Double
                                                        idTransaction =
                                                            data.transactionId.toString()

                                                        Log.i(
                                                            "UPDATE LOKASI",
                                                            "SUCCESSFULL UPDATE LOCATION $latitude, $longitude"
                                                        )
                                                        mMap.clear()

                                                        val markerInfo = listOf(
                                                            Point(
                                                                LatLng(latUser, longUser),
                                                                "Your Location",
                                                                getReadableLocation(
                                                                    latUser,
                                                                    longUser,
                                                                    this@MapsRunningUser
                                                                ),
                                                                R.drawable.form_point,
                                                                Color.parseColor("#2CBE21")
                                                            ),

                                                            Point(
                                                                LatLng(latitude, longitude),
                                                                "Driver Location",
                                                                getReadableLocation(
                                                                    latitude,
                                                                    longitude,
                                                                    this@MapsRunningUser
                                                                ),
                                                                R.drawable.truck_icon,
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
                                                                    .icon(
                                                                        vectorToBitmap(
                                                                            this@MapsRunningUser,
                                                                            it.iconResId,
                                                                            it.color
                                                                        )
                                                                    )
                                                            )
                                                        }

                                                        FetchURL(this@MapsRunningUser).execute(
                                                            getUrl(
                                                                LatLng(latitude, longitude),
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

                                                        mMap.animateCamera(
                                                            CameraUpdateFactory.newLatLngBounds(
                                                                bounds,
                                                                300
                                                            )
                                                        )
                                                    }


                                                    is Result.Error -> {
                                                        // INtent KE PAGE SELANJUTNY PENILAIAN
                                                        Toast.makeText(
                                                            this@MapsRunningUser,
                                                            "The driver has arrived and completed the order",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        IntentToFinishTransaction(
                                                            this@MapsRunningUser,
                                                            idTransaction,
                                                            idDriver
                                                        )
                                                    }

                                                    else -> {}
                                                }
                                            }
                                        }
                                }
                                handler.postDelayed(this, 30000)
                            }
                        }
                        handler.post(getDataRunnable)
                    }

                    else -> {
                    }
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
