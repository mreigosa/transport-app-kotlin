package com.mreigar.transportapp.view.transportresourcemap

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class TransportResourceMapEntity(
    val id: String,
    val name: String,
    val latLng: LatLng,
    val color: Float
) : ClusterItem{

    override fun getPosition(): LatLng = latLng

    override fun getTitle(): String = name

    override fun getSnippet(): String = ""

}