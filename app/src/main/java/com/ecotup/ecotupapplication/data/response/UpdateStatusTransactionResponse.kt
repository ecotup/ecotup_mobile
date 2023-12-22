package com.ecotup.ecotupapplication.data.response

import com.google.gson.annotations.SerializedName

data class UpdateStatusTransactionResponse(

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)
