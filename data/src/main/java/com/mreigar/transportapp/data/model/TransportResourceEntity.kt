package com.mreigar.transportapp.data.model

data class TransportResourceEntity(
    val id: String,
    val name: String,
    val latitude: Float,
    val longitude: Float,
    val companyZoneId: Int
)