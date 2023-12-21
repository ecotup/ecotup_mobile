package com.ecotup.ecotupapplication.data.response

import com.google.gson.annotations.SerializedName

data class InCompletedResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataItem(

	@field:SerializedName("transaction_id")
	val transactionId: Int? = null,

	@field:SerializedName("driver_id")
	val driverId: Int? = null,

	@field:SerializedName("transaction_longitude_destination")
	val transactionLongitudeDestination: Any? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("transaction_latitude_destination")
	val transactionLatitudeDestination: Any? = null,

	@field:SerializedName("transaction_longitude_start")
	val transactionLongitudeStart: Any? = null,

	@field:SerializedName("transaction_latitude_start")
	val transactionLatitudeStart: Any? = null
)
