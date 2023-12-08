package com.ecotup.ecotupapplication.data.response

import com.google.gson.annotations.SerializedName

data class LoginDriverResponse(

	@field:SerializedName("data")
	val data: DataDriver? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataDriver(

	@field:SerializedName("id_driver")
	val idDriver: Int? = null,

	@field:SerializedName("token")
	val token: String? = null
)
