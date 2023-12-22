package com.ecotup.ecotupapplication.data.response

import com.google.gson.annotations.SerializedName

data class DetailFinishTransactionResponse(

    @field:SerializedName("data")
    val data: DataFinishTransaction? = null,

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class DataFinishTransaction(

    @field:SerializedName("transaction_id")
    val transactionId: Int? = null,

    @field:SerializedName("transaction_total_weight")
    val transactionTotalWeight: Int? = null,

    @field:SerializedName("transaction_status")
    val transactionStatus: String? = null,

    @field:SerializedName("driver_id")
    val driverId: Int? = null,

    @field:SerializedName("transaction_latitude_destination")
    val transactionLatitudeDestination: Any? = null,

    @field:SerializedName("transaction_total_payment")
    val transactionTotalPayment: Int? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("transaction_longitude_start")
    val transactionLongitudeStart: Any? = null,

    @field:SerializedName("transaction_description")
    val transactionDescription: String? = null,

    @field:SerializedName("transaction_longitude_destination")
    val transactionLongitudeDestination: Any? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("user_id")
    val userId: Int? = null,

    @field:SerializedName("transaction_latitude_start")
    val transactionLatitudeStart: Any? = null,

    @field:SerializedName("transaction_total_point")
    val transactionTotalPoint: Int? = null
)
