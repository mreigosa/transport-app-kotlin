package com.mreigar.transportapp.view.transportresourcemap

import com.google.android.gms.maps.model.LatLng

data class TransportResourceMapEntity(
    val id: String,
    val name: String,
    val position: LatLng,
    val color: Float
)