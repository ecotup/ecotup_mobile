package com.ecotup.ecotupapplication.data.model

data class PersonModel(
    val id: String,
    val token: String,
    val role: String,
)

data class PersonModelData(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val lat: String,
    val long: String,
    val profile: String,
    val point: String,
    val subscription_date: String,
    val subscription_status: String,
    val subscription_value: String,
)

data class DriverModelData(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val lat: String,
    val long: String,
    val profile: String,
    val point: String,
    val type: String,
    val license: String,
    val rating: String,
)