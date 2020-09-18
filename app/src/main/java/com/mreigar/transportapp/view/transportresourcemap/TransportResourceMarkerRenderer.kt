package com.mreigar.transportapp.view.transportresourcemap

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

class TransportResourceMarkerRenderer(
    context: Context,
    map: GoogleMap?,
    clusterManager: ClusterManager<TransportResourceMapEntity>?
) : DefaultClusterRenderer<TransportResourceMapEntity>(context, map, clusterManager) {


    override fun onBeforeClusterItemRendered(item: TransportResourceMapEntity?, markerOptions: MarkerOptions?) {
        super.onBeforeClusterItemRendered(item, markerOptions)

        item?.let {
            markerOptions?.icon(BitmapDescriptorFactory.defaultMarker(it.color))
        }
    }
}