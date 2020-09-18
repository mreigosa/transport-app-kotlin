package com.mreigar.transportapp.view.transportresourcemap

import android.os.Bundle
import android.view.Window
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import com.mreigar.transportapp.R
import com.mreigar.transportapp.injection.injectActivity
import com.mreigar.transportapp.presentation.model.TransportResourceViewEntity
import com.mreigar.transportapp.presentation.presenter.transportresourcemap.MapVisibleRegion
import com.mreigar.transportapp.presentation.presenter.transportresourcemap.TransportResourceMapPresenter
import com.mreigar.transportapp.presentation.presenter.transportresourcemap.TransportResourceMapViewTranslator
import com.mreigar.transportapp.view.BaseActivity

class TransportResourceMapActivity : BaseActivity<TransportResourceMapPresenter>(), TransportResourceMapViewTranslator {

    companion object {
        private const val ZOOM_LEVEL = 16.5f
        private val DEFAULT_LOCATION = LatLng(38.736946, -9.142685) //harcoded somewhere in Lisboa
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
        mapFragment.getMapAsync { googleMap -> onMapReady(googleMap) }
    }

    override fun getMapVisibleRegion(): MapVisibleRegion = with(mMap.projection.visibleRegion.latLngBounds) {
        MapVisibleRegion(northeast.latitude, northeast.longitude, southwest.latitude, southwest.longitude)
    }

    private fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        clusterManager = ClusterManager(this, googleMap)
        clusterManager.renderer = TransportResourceMarkerRenderer(this, googleMap, clusterManager)

        mMap.apply {
            mapType = GoogleMap.MAP_TYPE_NORMAL
            setOnCameraIdleListener(::onCameraIdle)
            setOnMarkerClickListener(clusterManager)
            setOnInfoWindowClickListener(clusterManager)
            animateCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, ZOOM_LEVEL), object : GoogleMap.CancelableCallback {
                override fun onFinish() {
                    presenter.onMapRegionChanged()
                }

                override fun onCancel() {}
            })
        }
    }

    private fun onCameraIdle() {
        clusterManager.onCameraIdle()
        presenter.onMapRegionChanged()
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