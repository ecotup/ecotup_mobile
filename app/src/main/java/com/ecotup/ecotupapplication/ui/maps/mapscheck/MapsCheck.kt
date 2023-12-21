package com.ecotup.ecotupapplication.ui.maps.mapscheck

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.cammon.Result
import com.ecotup.ecotupapplication.data.model.FindDriverModel
import com.ecotup.ecotupapplication.data.vmf.ServiceViewModelFactory
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.databinding.ActivityMapsCheckBinding
import com.ecotup.ecotupapplication.ui.user.home.HomeUserViewModel
import com.ecotup.ecotupapplication.util.IntentToLoadingFindDriver
import com.ecotup.ecotupapplication.util.getReadableLocation
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsCheck : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsCheckBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val viewModel by viewModels<MapsCheckViewModel> {
        ServiceViewModelFactory.getInstance(this@MapsCheck)
    }
    private val homeViewModel by viewModels<HomeUserViewModel> {
        ViewModelFactory.getInstance(this@MapsCheck)
    }

    private lateinit var idUser: String
    private var latUser: Double = 0.0
    private var longUser: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        homeViewModel.getSessionUser().observe(this) {
            idUser = it.id
        }
        findDriver()
    }


    private fun findDriver() {
        binding.btnFindDriver.setOnClickListener {
            viewModel.findDriver(idUser.toInt()).observe(this@MapsCheck)
            { result ->
                when (result) {
                    is Result.Loading -> {

                    }

                    is Result.Success -> {
                        val data = result.data
                        val idDriver = data.nearestDriverId
                        val idUser = data.userId
                        val lat = data.driverLatitude
                        val long = data.driverLongitude
                        val distance = data.distance

                        Log.d("MapsCheck", "onCreate: $data")
                        viewModel.setDriver(
                            FindDriverModel(
                                idDriver.toString(),
                                idUser.toString(),
                                lat.toString(),
                                long.toString(),
                                distance.toString()
                            )
                        )
                        IntentToLoadingFindDriver(this@MapsCheck)
                    }

                    is Result.Error -> {

                    }

                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true
        getLocation()
    }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false -> {
                    getLocation()
                }

                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> {
                    getLocation()
                }

                else -> {
                }
            }
        }

    private fun getLocation() {
        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION) &&
            checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            homeViewModel.getDetailUser(idUser).observe(this@MapsCheck)
            { result ->
                if (result != null) {
                    when (result) {
                        is Result.Success -> {
                            latUser = result.data.data?.userLatitude as Double
                            longUser = result.data.data?.userLongitude as Double
                            showStartMarker(latUser, longUser)
                            locationUser()
                        }

                        else -> {}
                    }
                }

            }

        } else {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private fun locationUser() {
        binding.tvLocationUser.text = getReadableLocation(latUser, longUser, this@MapsCheck)
    }

    private fun showStartMarker(lat: Double, long: Double) {
        val startLocation = LatLng(lat, long)
        mMap.addMarker(
            MarkerOptions()
                .position(startLocation)
                .title("Your Location")
                .icon(vectorToBitmap(this, R.drawable.form_point, Color.parseColor("#2CBE21")))
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 17f))
    }

    private fun vectorToBitmap(
        context: Context, @DrawableRes id: Int, @ColorInt color: Int
    ): BitmapDescriptor {
        val vectorDrawable = ResourcesCompat.getDrawable(context.resources, id, null)
        if (vectorDrawable == null) {
            Log.e("BitmapHelper", "Resource not found")
            return BitmapDescriptorFactory.defaultMarker()
        }
        val size = 75
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