package com.mreigar.transportapp.presentation.model

import com.mreigar.transportapp.domain.model.TransportResourceType

data class TransportResourceViewEntity(
    val id: String,
    val name: String,
    val latitude: Float,
    val longitude: Float,
    val type: TransportResourceType
)