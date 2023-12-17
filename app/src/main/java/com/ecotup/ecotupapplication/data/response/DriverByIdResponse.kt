package com.ecotup.ecotupapplication.data.response

import com.google.gson.annotations.SerializedName

data class DriverByIdResponse(

	@field:SerializedName("data")
	val data: DataDriverEcotup? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataDriverEcotup(

	@field:SerializedName("transaction_id")
	val transactionId: Any? = null,

	@field:SerializedName("driver_id")
	val driverId: Int? = null,

	@field:SerializedName("driver_type")
	val driverType: String? = null,

	@field:SerializedName("driver_password")
	val driverPassword: String? = null,

	@field:SerializedName("driver_profile")
	val driverProfile: Any? = null,

	@field:SerializedName("driver_email")
	val driverEmail: String? = null,

	@field:SerializedName("driver_point")
	val driverPoint: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("driver_rating")
	val driverRating: Int? = null,

	@field:SerializedName("driver_phone")
	val driverPhone: String? = null,

	@field:SerializedName("driver_name")
	val driverName: String? = null,

	@field:SerializedName("cluster_id")
	val clusterId: Any? = null,

	@field:SerializedName("driver_longitude")
	val driverLongitude: Any? = null,

	@field:SerializedName("driver_token")
	val driverToken: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("driver_latitude")
	val driverLatitude: Any? = null,

	@field:SerializedName("driver_license")
	val driverLicense: String? = null
)
