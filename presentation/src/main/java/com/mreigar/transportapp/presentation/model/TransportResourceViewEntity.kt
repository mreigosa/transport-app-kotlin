package com.mreigar.transportapp.presentation.model

data class TransportResourceViewEntity(
    val id: String,
    val name: String,
    val latitude: Float,
    val longitude: Float,
    val companyZoneId: Int
)