package com.mreigar.transportapp.view.transportresourcemap

import android.os.Bundle
import android.transition.TransitionManager
import android.view.Window
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.mreigar.transportapp.R
import com.mreigar.transportapp.injection.injectActivity
import com.mreigar.transportapp.presentation.model.TransportResourceViewEntity
import com.mreigar.transportapp.presentation.presenter.transportresourcemap.MapVisibleRegion
import com.mreigar.transportapp.presentation.presenter.transportresourcemap.TransportResourceMapPresenter
import com.mreigar.transportapp.presentation.presenter.transportresourcemap.TransportResourceMapViewTranslator
import com.mreigar.transportapp.view.BaseActivity
import kotlinx.android.synthetic.main.activity_transport_resource_map.*


class TransportResourceMapActivity : BaseActivity<TransportResourceMapPresenter>(), TransportResourceMapViewTranslator,
    ClusterManager.OnClusterItemClickListener<TransportResourceMapEntity>,
    ClusterManager.OnClusterClickListener<TransportResourceMapEntity> {

    companion object {
        private const val ZOOM_LEVEL = 16.5f
        private val DEFAULT_LOCATION = LatLng(38.736946, -9.142685) //harcoded somewhere in Lisboa
    }

    override val presenter: TransportResourceMapPresenter by injectActivity()

    private lateinit var mMap: GoogleMap
    private lateinit var clusterManager: ClusterManager<TransportResourceMapEntity>

    private val mapper = TransportResourceMapEntityMapper()

    private var bannerExpanded: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_transport_resource_map)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { googleMap -> onMapReady(googleMap) }

        transportMapBanner.setOnClickListener { hideTransportMapBanner() }
    }

    override fun getMapVisibleRegion(): MapVisibleRegion = with(mMap.projection.visibleRegion.latLngBounds) {
        MapVisibleRegion(northeast.latitude, northeast.longitude, southwest.latitude, southwest.longitude)
    }

    private fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        clusterManager = ClusterManager(this, googleMap)
        clusterManager.apply {
            renderer = TransportResourceMarkerRenderer(this@TransportResourceMapActivity, googleMap, clusterManager)
            setOnClusterItemClickListener(this@TransportResourceMapActivity)
        }

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

    override fun onClusterItemClick(item: TransportResourceMapEntity): Boolean {
        showTransportResourceMapBanner(item.name, item.id)
        return true
    }

    override fun onClusterClick(cluster: Cluster<TransportResourceMapEntity>?): Boolean {
        return true
    }

    private fun showTransportResourceMapBanner(title: String, subtitle: String) {
        transportResourceMapBannerTitle.text = title
        transportResourceMapBannerSubtitle.text = subtitle

        if (!bannerExpanded) {
            val constraint = ConstraintSet()
            constraint.clone(transportMapLayout)

            TransitionManager.beginDelayedTransition(transportMapBanner)
            constraint.clear(transportMapBanner.id, ConstraintSet.TOP)
            constraint.connect(transportMapBanner.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
            constraint.applyTo(transportMapLayout)
            bannerExpanded = true
        }
    }

    private fun hideTransportMapBanner() {
        val constraint = ConstraintSet()
        constraint.clone(transportMapLayout)

        TransitionManager.beginDelayedTransition(transportMapBanner)
        constraint.clear(transportMapBanner.id, ConstraintSet.BOTTOM)
        constraint.connect(transportMapBanner.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
        constraint.applyTo(transportMapLayout)
        bannerExpanded = false
    }
}