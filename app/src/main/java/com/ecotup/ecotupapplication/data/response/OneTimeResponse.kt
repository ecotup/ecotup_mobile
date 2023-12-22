package com.ecotup.ecotupapplication.data.response

import com.google.gson.annotations.SerializedName

data class OneTimeResponse(
    @field:SerializedName("nearest_driver_id")
    val nearestDriverId: Int? = null,

    @field:SerializedName("driver_longitude")
    val driverLongitude: Any? = null,

    @field:SerializedName("distance")
    val distance: Any? = null,

    @field:SerializedName("user_id")
    val userId: Int? = null,

    @field:SerializedName("driver_latitude")
    val driverLatitude: Any? = null
)
