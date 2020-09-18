package com.mreigar.transportapp.data.model

import com.mreigar.transportapp.domain.model.TransportResourceType

data class TransportResourceEntity(
    val id: String,
    val name: String,
    val latitude: Float,
    val longitude: Float,
    val type: TransportResourceType
)