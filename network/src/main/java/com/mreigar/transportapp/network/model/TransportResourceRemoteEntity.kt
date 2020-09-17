package com.mreigar.transportapp.network.model

import com.google.gson.annotations.SerializedName

data class TransportResourceRemoteEntity(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("lat") val latitude: Float,
    @SerializedName("lon") val longitude: Float,
    @SerializedName("companyZoneId") val companyZoneId: Int
)