package com.mreigar.transportapp.presentation.presenter.transportresourcemap

import com.mreigar.transportapp.domain.executor.Invoker
import com.mreigar.transportapp.domain.executor.Success
import com.mreigar.transportapp.domain.executor.usecase.GetTransportsAroundLocationUseCase
import com.mreigar.transportapp.domain.executor.usecase.GetTransportsAroundLocationUseCaseParams
import com.mreigar.transportapp.presentation.mapper.TransportResourceViewEntityMapper
import com.mreigar.transportapp.presentation.presenter.BasePresenter
import java.lang.ref.WeakReference

class TransportResourceMapPresenter(
    view: TransportResourceMapViewTranslator,
    private val getTransportsAroundLocationUseCase: GetTransportsAroundLocationUseCase,
    invoker: Invoker
) : BasePresenter<TransportResourceMapViewTranslator>(WeakReference(view), invoker) {

    private val mapper: TransportResourceViewEntityMapper = TransportResourceViewEntityMapper()

    fun onMapRegionChanged() {
        if (view()?.isNetworkAvailable() == true) {
            view()?.hideNoInternetAvailable()
            view()?.getMapVisibleRegion()?.let {
                fetchTransportResources(it.northEastLatitude, it.northEastLongitude, it.southWestLatitude, it.southWesLongitude)
            }
        } else {
            view()?.showNoInternetAvailable()
        }
    }

    private fun fetchTransportResources(northEastLatitude: Double, northEastLongitude: Double, southWestLatitude: Double, southWesLongitude: Double) {
        val params = GetTransportsAroundLocationUseCaseParams(
            leftLatLong = "$southWestLatitude,$southWesLongitude",
            rightLatLong = "$northEastLatitude,$northEastLongitude",
        )

        invoker.execute(this, getTransportsAroundLocationUseCase withParams params) { result ->
            when (result) {
                is Success -> view()?.showTransportResources(result.data.map { mapper.mapToViewEntity(it) })
                else -> view()?.showError()
            }
        }
    }
}

