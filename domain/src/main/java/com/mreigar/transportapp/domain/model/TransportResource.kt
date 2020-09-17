package com.mreigar.transportapp.domain.model

data class TransportResource(
    val id: String,
    val name: String,
    val latitude: Float,
    val longitude: Float,
    val companyZoneId: Int
)