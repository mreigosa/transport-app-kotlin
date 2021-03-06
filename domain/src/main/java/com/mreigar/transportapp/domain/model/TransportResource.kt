package com.mreigar.transportapp.domain.model

data class TransportResource(
    val id: String,
    val name: String,
    val latitude: Float,
    val longitude: Float,
    val type: TransportResourceType
)


//in the real app these would have more meaningful names like RESOURCE_STATION or RESOURCE_BIKE
enum class TransportResourceType {
    RESOURCE_UNKNOWN, RESOURCE_402, RESOURCE_378, RESOURCE_382, RESOURCE_467, RESOURCE_473, RESOURCE_412
}