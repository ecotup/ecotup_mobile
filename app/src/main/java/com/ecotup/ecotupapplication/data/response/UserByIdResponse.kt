package com.ecotup.ecotupapplication.data.response

import com.google.gson.annotations.SerializedName

data class UserByIdResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Data(

	@field:SerializedName("transaction_id")
	val transactionId: Any? = null,

	@field:SerializedName("user_password")
	val userPassword: String? = null,

	@field:SerializedName("user_email")
	val userEmail: String? = null,

	@field:SerializedName("subscription_value")
	val subscriptionValue: Int? = null,

	@field:SerializedName("user_name")
	val userName: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("user_profile")
	val userProfile: String? = null,

	@field:SerializedName("user_longitude")
	val userLongitude: Any? = null,

	@field:SerializedName("user_subscription_date")
	val userSubscriptionDate: String? = null,

	@field:SerializedName("user_latitude")
	val userLatitude: Any? = null,

	@field:SerializedName("subscription_id")
	val subscriptionId: Int? = null,

	@field:SerializedName("cluster_id")
	val clusterId: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("subscription_status")
	val subscriptionStatus: String? = null,

	@field:SerializedName("user_phone")
	val userPhone: String? = null,

	@field:SerializedName("user_point")
	val userPoint: Int? = null,

	@field:SerializedName("user_token")
	val userToken: String? = null
)
