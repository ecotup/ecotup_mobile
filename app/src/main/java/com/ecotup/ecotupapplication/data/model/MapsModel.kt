package com.ecotup.ecotupapplication.data.model

import com.google.android.gms.maps.model.LatLng

data class FindDriverModel(
    val idDriver: String,
    val idUser: String,
    val latitude: String,
    val longitude: String,
    val distance: String
)

data class Point(
    val position: LatLng,
    val title: String,
    val snippet: String,
    val iconResId: Int,
    val color: Int
)
