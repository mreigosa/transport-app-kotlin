package com.mreigar.transportapp.view.transportresourcemap

import android.os.Bundle
import android.view.Window
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.mreigar.transportapp.R
import com.mreigar.transportapp.injection.injectActivity
import com.mreigar.transportapp.presentation.model.TransportResourceViewEntity
import com.mreigar.transportapp.presentation.presenter.transportresourcemap.TransportResourceMapPresenter
import com.mreigar.transportapp.presentation.presenter.transportresourcemap.TransportResourceMapViewTranslator
import com.mreigar.transportapp.view.BaseActivity

class TransportResourceMapActivity : BaseActivity<TransportResourceMapPresenter>(), TransportResourceMapViewTranslator {

    companion object {
        const val ZOOM_LEVEL = 17f
    }

    override val presenter: TransportResourceMapPresenter by injectActivity()

    private lateinit var mMap: GoogleMap
    private lateinit var clusterManager: ClusterManager<TransportResourceMapEntity>

    private val mapper = TransportResourceMapEntityMapper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_transport_resource_map)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            mMap = googleMap
            clusterManager = ClusterManager(this, googleMap)
            clusterManager.renderer = TransportResourceMarkerRenderer(this, googleMap, clusterManager)

            mMap.apply {
                mapType = GoogleMap.MAP_TYPE_NORMAL
                setOnCameraIdleListener(clusterManager)
                setOnMarkerClickListener(clusterManager)
                setOnInfoWindowClickListener(clusterManager)
            }

            val lisboa = LatLng(38.711046, -9.160096)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lisboa, ZOOM_LEVEL))
        }
    }

    override fun showTransportResources(transportResources: List<TransportResourceViewEntity>) {
        val mapItems = transportResources.map { mapper.mapToMapEntity(it) }

        with(clusterManager) {
            clearItems()
            addItems(mapItems)
            cluster()
        }
    }

    override fun showError() {

    }
}