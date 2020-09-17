package com.mreigar.transportapp.presentation.presenter.transportresourcemap

import com.mreigar.transportapp.presentation.model.TransportResourceViewEntity

interface TransportResourceMapViewTranslator {
    fun showTransportResources(transportResources: List<TransportResourceViewEntity>)
    fun showError()
}