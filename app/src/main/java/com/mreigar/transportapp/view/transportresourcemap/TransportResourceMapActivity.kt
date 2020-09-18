package com.mreigar.transportapp.view.transportresourcemap

import android.os.Bundle
import android.view.Window
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mreigar.transportapp.R
import com.mreigar.transportapp.injection.injectActivity
import com.mreigar.transportapp.presentation.model.TransportResourceViewEntity
import com.mreigar.transportapp.presentation.presenter.transportresourcemap.TransportResourceMapPresenter
import com.mreigar.transportapp.presentation.presenter.transportresourcemap.TransportResourceMapViewTranslator
import com.mreigar.transportapp.view.BaseActivity

class TransportResourceMapActivity : BaseActivity<TransportResourceMapPresenter>(), TransportResourceMapViewTranslator {

    companion object {
        const val ZOOM_LEVEL = 16f
    }

    override val presenter: TransportResourceMapPresenter by injectActivity()

    private lateinit var mMap: GoogleMap

    private val mapper = TransportResourceMapEntityMapper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_transport_resource_map)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            mMap = googleMap
            val lisboa = LatLng(38.711046, -9.160096)
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(lisboa))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lisboa, ZOOM_LEVEL))
            //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lisboa, ZOOM_LEVEL))
        }
    }

    override fun showTransportResources(transportResources: List<TransportResourceViewEntity>) {
        val mapItems = transportResources.map { mapper.mapToMapEntity(it) }
        for (item in mapItems) {
            mMap.addMarker(
                MarkerOptions()
                    .position(item.position)
                    .title(item.name)
                    .icon(BitmapDescriptorFactory.defaultMarker(item.color))
            )
        }
    }

    override fun showError() {

    }
}