package com.mreigar.transportapp.presentation.presenter.transportresourcemap

import com.mreigar.transportapp.presentation.model.TransportResourceViewEntity

interface TransportResourceMapViewTranslator {
    fun showTransportResources(transportResources: List<TransportResourceViewEntity>)
    fun showError()
    fun getMapVisibleRegion(): MapVisibleRegion
}

data class MapVisibleRegion(
    val northEastLatitude: Double,
    val northEastLongitude: Double,
    val southWestLatitude: Double,
    val southWesLongitude: Double
)