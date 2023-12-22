package com.ecotup.ecotupapplication.ui.maps.mapsdriver

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import cn.pedant.SweetAlert.SweetAlertDialog
import com.ecotup.ecotupapplication.BuildConfig
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.cammon.Result
import com.ecotup.ecotupapplication.data.model.Point
import com.ecotup.ecotupapplication.data.response.DataTransaction
import com.ecotup.ecotupapplication.data.vmf.ServiceViewModelFactory
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.databinding.ActivityMapsDriverOneTimeBinding
import com.ecotup.ecotupapplication.ui.general.login.LoginViewModel
import com.ecotup.ecotupapplication.ui.user.home.HomeUserViewModel
import com.ecotup.ecotupapplication.ui.user.scan.ScanningUserViewModel
import com.ecotup.ecotupapplication.util.IntentToMain
import com.ecotup.ecotupapplication.util.directionhelper.FetchURL
import com.ecotup.ecotupapplication.util.directionhelper.TaskLoadedCallback
import com.ecotup.ecotupapplication.util.getFoto
import com.ecotup.ecotupapplication.util.getReadableLocation
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
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
import kotlinx.coroutines.runBlocking
import java.net.URLEncoder
import java.util.concurrent.TimeUnit

class MapsDriverOneTime : AppCompatActivity(), OnMapReadyCallback, TaskLoadedCallback {

    private lateinit var mMap: GoogleMap
    private var currentPolyline: Polyline? = null
    private lateinit var binding: ActivityMapsDriverOneTimeBinding
    private val viewModel by viewModels<MapsDriverOneTimeViewModel> {
        ServiceViewModelFactory.getInstance(this@MapsDriverOneTime)
    }
    private val viewModel2 by viewModels<MapsDriverOneTimeViewModel2> {
        ViewModelFactory.getInstance(this@MapsDriverOneTime)
    }
    private val homeViewModel by viewModels<HomeUserViewModel> {
        ViewModelFactory.getInstance(this@MapsDriverOneTime)
    }
    private val loginViewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this@MapsDriverOneTime)
    }
    private val scanningViewModel by viewModels<ScanningUserViewModel> {
        ViewModelFactory.getInstance(this@MapsDriverOneTime)
    }

    private var latDriver: Double = 0.0
    private var longDriver: Double = 0.0

    private var description: String = ""
    private var total_payment: Double = 0.0
    private var total_weight: Double = 0.0
    private var total_point: Int = 0
    private var idTrans: String = ""

    private var dataTransaction: DataTransaction = DataTransaction(
        transactionId = null,
        transactionTotalWeight = null,
        transactionStatus = null,
        driverId = null,
        transactionLatitudeDestination = null,
        transactionTotalPayment = null,
        createdAt = null,
        transactionLongitudeStart = null,
        transactionDescription = null,
        transactionLongitudeDestination = null,
        updatedAt = null,
        userId = null,
        transactionLatitudeStart = null,
        transactionTotalPoint = null
    )


    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private var isTracking = false
    private lateinit var locationCallback: LocationCallback
    private var allLatLng = ArrayList<LatLng>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsDriverOneTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val idTransaction = intent.getStringExtra("idTransaction").toString()
        idTrans = idTransaction
        Log.d("MMM", "$dataTransaction")
        // penentuan titik pertama kali
        getDataTransaksi(idTransaction)


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val handler = Handler(Looper.getMainLooper())
        val getDataRunnable = object : Runnable {
            override fun run() {
                getDataTransaksi(idTransaction)
                handler.postDelayed(this, 30000)
            }
        }
        handler.post(getDataRunnable)


    }

    private fun finishOrder(userId: String, driverId: String) {
        binding.btnFinishOrder.setOnClickListener {
            val sweetAlertDialog =
                SweetAlertDialog(
                    this,
                    SweetAlertDialog.CUSTOM_IMAGE_TYPE
                ).setCustomImage(R.drawable.ask)
                    .setTitleText("Finish Order?")
                    .setContentText("Are you sure you want to complete the order ?")
                    .setConfirmButton("OK") {
                        val sweetAlertDialog =
                            SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Congratulations !")
                                .setContentText("Order completed successfully")
                                .setConfirmButton("OK") { child ->
                                    // Paused Updated location
                                    isTracking = false
                                    updatePointUserDriver(userId, driverId)
                                    child.dismissWithAnimation()
                                }
                        sweetAlertDialog.setCancelable(false)
                        sweetAlertDialog.show()
                        it.dismissWithAnimation()
                    }
                    .setCancelButton("Cancel") {
                        it.dismissWithAnimation()
                    }
            sweetAlertDialog.setCancelable(true)
            sweetAlertDialog.show()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true

        createLocationRequest()
        createLocationCallback()
    }

    private fun updatePointUserDriver(userId: String, driverId: String) {
        runBlocking {
            loginViewModel.getDetailUser(userId).observe(this@MapsDriverOneTime)
            { user ->
                if (user != null) {
                    when (user) {
                        is Result.Loading -> {
                            Log.d("MMM", "Loading Update getPoint User")
                        }

                        is Result.Error -> {
                            Log.d("MMM", "Error Update getPoint User")
                        }

                        is Result.Success -> {
                            runBlocking {
                                Log.d(
                                    "MMM",
                                    "Success Update getPoint User ${user.data.data?.userPoint as Int + total_point}"
                                )
                                scanningViewModel.updatePoint(
                                    userId,
                                    (user.data.data?.userPoint as Int + total_point)
                                )
                                    .observe(this@MapsDriverOneTime)
                                    { result ->
                                        if (result != null) {
                                            when (result) {
                                                is Result.Loading -> {
                                                    Log.d("MMM", "Loading Update Point User")
                                                }

                                                is Result.Success -> {
                                                    Log.d("MMM", "Success Update Point User")
                                                }

                                                is Result.Error -> {
                                                    Log.d("MMM", "Error Update Point User")
                                                }

                                                else -> {}
                                            }
                                        }
                                    }
                            }
                        }

                        else -> {}
                    }
                }

            }

        }


        runBlocking {
            loginViewModel.getDetailDriver(driverId).observe(this@MapsDriverOneTime)
            { driver ->
                if (driver != null) {
                    when (driver) {
                        is Result.Loading -> {
                            Log.d("MMM", "Loading Update getPoint Driver")
                        }

                        is Result.Error -> {
                            Log.d("MMM", "Error Update getPoint Driver")
                        }

                        is Result.Success -> {
                            runBlocking {
                                Log.d(
                                    "MMM",
                                    "Success Update getPoint User ${driver.data.data?.driverPoint as Int + total_point}"
                                )
                                viewModel2.updatePointDriver(
                                    driverId,
                                    (driver.data.data?.driverPoint as Int + total_point)
                                )
                                    .observe(this@MapsDriverOneTime)
                                    { result ->
                                        if (result != null) {
                                            when (result) {
                                                is Result.Loading -> {
                                                    Log.d("MMM", "Loading Update Point Driver")

                                                }

                                                is Result.Success -> {
                                                    Log.d("MMM", "Success Update Point Driver")
                                                    updateStatusTransaksi(idTrans)
                                                    IntentToMain(this@MapsDriverOneTime)
                                                }

                                                is Result.Error -> {
                                                    Log.d("MMM", "Error Update Point Driver")
                                                }
                                            }
                                        }
                                    }
                            }
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun updateStatusTransaksi(idTransaction: String) {
        runBlocking {
            viewModel2.updateStatusTransaksi(idTransaction, "Completed")
                .observe(this@MapsDriverOneTime)
                { result ->
                    if (result != null) {
                        when (result) {
                            is Result.Loading -> {}
                            is Result.Success -> {
                                Toast.makeText(
                                    this@MapsDriverOneTime,
                                    "Transaction Completed",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }

                            is Result.Error -> {}
                        }
                    }
                }

        }
    }

    private fun getDataTransaksi(idTransaction: String) {
        runBlocking {
            viewModel2.getTransaksiByIdTransaksi(idTransaction)
                .observe(this@MapsDriverOneTime) { result ->
                    if (result != null) {
                        when (result) {
                            is Result.Loading -> {
                                Log.d("MMM", "LOADINGGGG")
                            }

                            is Result.Success -> {
                                Log.d("MMM", "SUCCESS")
                                val response = result.data.data
                                dataTransaction = response!!
                                Log.d("MMM", "$response")

                                // set Layoutny
                                setDataLayout()

                                // MAIN DI SINI
                                finishOrder(
                                    dataTransaction.userId.toString(),
                                    dataTransaction.driverId.toString()
                                )

                                // tetapkan posisi


                                // lat long update 30 detik 1 kali
                                updateLokasiDriver(
                                    dataTransaction.transactionId.toString(),
                                    dataTransaction.transactionLatitudeDestination as Double,
                                    dataTransaction.transactionLongitudeDestination as Double,
                                    dataTransaction.transactionLatitudeStart as Double,
                                    dataTransaction.transactionLongitudeStart as Double
                                )


                            }

                            is Result.Error -> {
                                Log.d("MMM", "ERROR ${result.errorMessage.toString()}")
                            }
                        }
                    }
                }
        }
    }

    private fun setDataLayout() {
        loginViewModel.getDetailUser(dataTransaction.userId.toString()).observe(this)
        { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {}
                    is Result.Success -> {
                        val response = result.data.data
                        binding.tvNameUser.text = response?.userName.toString()
                        getFoto(
                            this,
                            response?.userProfile.toString(),
                            binding.ivProfileUser
                        )
                        binding.llCallUser.setOnClickListener {
                            val message = "Hallo,\n" +
                                    "I will move to your place to pick up the trash\n\n" +
                                    "Please prepare the rubbish that will be thrown into the rubbish bin.\n\n" +
                                    "As a suggestion, you can separate your waste using the trash scan feature so that you get points from this application which can be exchanged for various options."
                            val url =
                                "https://api.whatsapp.com/send?phone=${response?.userPhone.toString()}&text=${
                                    URLEncoder.encode(
                                        message,
                                        "UTF-8"
                                    )
                                }"
                            val i = Intent(Intent.ACTION_VIEW)
                            i.data = Uri.parse(url)
                            startActivity(i)
                        }

                        binding.tvLocationUser.text =
                            getReadableLocation(
                                dataTransaction.transactionLatitudeDestination as Double,
                                dataTransaction.transactionLongitudeDestination as Double,
                                this
                            )
                        binding.tvLocationDriver.text =
                            getReadableLocation(
                                dataTransaction.transactionLatitudeStart as Double,
                                dataTransaction.transactionLongitudeStart as Double,
                                this
                            )

                        binding.tvWeight.text =
                            "Weight : ${dataTransaction.transactionTotalWeight.toString()} Kg"
                        binding.tvDescription.text =
                            "Description : ${dataTransaction.transactionDescription.toString()}"
                        binding.tvPayment.text =
                            "Payment : Rp. ${dataTransaction.transactionTotalPayment.toString()}"
                        Log.d("MMM", "LAT : ${dataTransaction.transactionLatitudeStart as Double} ")
                        Log.d(
                            "MMM",
                            "LONG : ${dataTransaction.transactionTotalPoint as Int} "
                        )

                        total_point = dataTransaction.transactionTotalPoint as Int

                    }

                    is Result.Error -> {}
                }
            }
        }
    }

    private fun updateLokasiDriver(
        idTransaksi: String,
        latU: Double,
        longU: Double,
        latD: Double,
        longD: Double
    ) {

        val latitudeDriver = if (latDriver == 0.0) latD else latDriver
        val longitudeDriver = if (longDriver == 0.0) longD else longDriver

        // update data ke database
        runBlocking {
            viewModel2.updateLatLongDriver(
                idTransaksi,
                latitudeDriver,
                longitudeDriver
            )
                .observe(this@MapsDriverOneTime)
                { result ->
                    if (result != null) {
                        when (result) {
                            is Result.Loading -> {}
                            is Result.Success -> {
                                mMap.clear()
                                val markerInfo = listOf(
                                    Point(
                                        LatLng(latU, longU),
                                        "User Location",
                                        getReadableLocation(
                                            latU,
                                            longU,
                                            this@MapsDriverOneTime
                                        ),
                                        R.drawable.form_point,
                                        Color.parseColor("#2CBE21")
                                    ),

                                    Point(
                                        LatLng(latitudeDriver, longitudeDriver),
                                        "Driver Location",
                                        getReadableLocation(
                                            latitudeDriver,
                                            longitudeDriver,
                                            this@MapsDriverOneTime
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
                                                    this@MapsDriverOneTime,
                                                    it.iconResId,
                                                    it.color
                                                )
                                            )
                                    )
                                }

                                FetchURL(this@MapsDriverOneTime).execute(
                                    getUrl(
                                        LatLng(latitudeDriver, longitudeDriver),
                                        LatLng(latU, longU),
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

                            is Result.Error -> {}
                            else -> {}
                        }
                    }
                }
        }
//                handler.postDelayed(this, 30000)
//            }
//        }
//        handler.post(getDataRunnable)
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


    /// Location
    private fun createLocationRequest() {
        locationRequest = LocationRequest.create().apply {
            interval = TimeUnit.SECONDS.toMillis(1)
            maxWaitTime = TimeUnit.SECONDS.toMillis(1)
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        val client = LocationServices.getSettingsClient(this)
        client.checkLocationSettings(builder.build())
            .addOnSuccessListener {
                getLocation()
            }
            .addOnFailureListener { exception ->
                if (exception is ResolvableApiException) {
                    try {
                        resolutionLauncher.launch(
                            IntentSenderRequest.Builder(exception.resolution).build()
                        )
                    } catch (sendEx: IntentSender.SendIntentException) {
                        Toast.makeText(this@MapsDriverOneTime, sendEx.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

    }

    private fun createLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                // Hapus semua marker sebelum menambahkan yang baru
                runBlocking {
                    mMap.clear()
                    for (location in locationResult.locations) {
                        Log.d(
                            TAG,
                            "onLocationResult: " + location.latitude + ", " + location.longitude
                        )
                        latDriver = location.latitude
                        longDriver = location.longitude
                    }
                }
            }
        }
    }

    private val resolutionLauncher = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        when (result.resultCode) {
            RESULT_OK -> {
                Log.i(TAG, "onActivityResult:  All location settings are satisfied.")
            }

            RESULT_CANCELED -> {
                Toast.makeText(
                    this@MapsDriverOneTime,
                    "Anda harus mengaktifkan GPS untuk menggunakan aplikasi ini!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {

            }
        }

    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permission ->
        when {
            permission[Manifest.permission.ACCESS_FINE_LOCATION] ?: false -> {
                //getLocation()
            }

            permission[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> {
                //getLocation()
            }

            else -> {

            }
        }
    }

    private fun checkPerission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this, permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun getLocation() {
        if (checkPerission(Manifest.permission.ACCESS_FINE_LOCATION) &&
            checkPerission(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    latDriver = location.latitude
                    longDriver = location.longitude
                } else {
                    Toast.makeText(
                        this@MapsDriverOneTime,
                        "Location is not found. Try Again",
                        Toast.LENGTH_SHORT
                    ).show()
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

    private fun startLocationUpdates() {
        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } catch (exception: SecurityException) {
            Log.e(TAG, "Error : " + exception.message)
        }
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onResume() {
        super.onResume()
        if (isTracking) {
            startLocationUpdates()
        }
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    companion object {
        private const val TAG = "Maps One Time"
    }
}