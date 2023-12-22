package com.ecotup.ecotupapplication.data.response

import com.google.gson.annotations.SerializedName

data class RewardResponse(

    @field:SerializedName("data")
    val data: List<DataReward>,

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class DataReward(

    @field:SerializedName("reward_price")
    val rewardPrice: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("reward_name")
    val rewardName: String? = null,

    @field:SerializedName("reward_image")
    val rewardImage: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("reward_id")
    val rewardId: Int? = null,

    @field:SerializedName("reward_description")
    val rewardDescription: String? = null
)
