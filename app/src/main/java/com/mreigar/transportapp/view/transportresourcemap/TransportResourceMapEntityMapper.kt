package com.mreigar.transportapp.view.transportresourcemap

import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.mreigar.transportapp.domain.model.TransportResourceType
import com.mreigar.transportapp.presentation.model.TransportResourceViewEntity

class TransportResourceMapEntityMapper {

    fun mapToMapEntity(viewEntity: TransportResourceViewEntity): TransportResourceMapEntity = with(viewEntity) {
        TransportResourceMapEntity(id, name, LatLng(latitude.toDouble(), longitude.toDouble()), getMarkerColor(type))
    }

    private fun getMarkerColor(type: TransportResourceType): Float {
        return when (type) {
            TransportResourceType.RESOURCE_402 -> BitmapDescriptorFactory.HUE_MAGENTA
            TransportResourceType.RESOURCE_378 -> BitmapDescriptorFactory.HUE_AZURE
            TransportResourceType.RESOURCE_382 -> BitmapDescriptorFactory.HUE_YELLOW
            TransportResourceType.RESOURCE_467 -> BitmapDescriptorFactory.HUE_GREEN
            TransportResourceType.RESOURCE_473 -> BitmapDescriptorFactory.HUE_CYAN
            TransportResourceType.RESOURCE_412 -> BitmapDescriptorFactory.HUE_BLUE
            TransportResourceType.RESOURCE_UNKNOWN -> BitmapDescriptorFactory.HUE_ORANGE
        }
    }
}