package com.ecotup.ecotupapplication.data.response

import com.google.gson.annotations.SerializedName

data class ClusterResponse(
	@field:SerializedName("cluster1")
	val cluster1: List<List<Any>>,

	@field:SerializedName("cluster0")
	val cluster0: List<List<Any>>,

	@field:SerializedName("cluster2")
	val cluster2: List<List<Any>>
)
