package com.ecotup.ecotupapplication.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
	@field:SerializedName("data")
	val data: DataUser? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataUser(
	@field:SerializedName("id_user")
	val idUser: Int? = null,

	@field:SerializedName("token")
	val token: String? = null
)
