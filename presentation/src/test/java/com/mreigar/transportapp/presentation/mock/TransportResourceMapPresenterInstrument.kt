package com.mreigar.transportapp.presentation.mock

import com.mreigar.transportapp.domain.mock.DomainInstrument.givenInvoker
import com.mreigar.transportapp.domain.mock.RepositoryStatus
import com.mreigar.transportapp.presentation.BaseCallbackResult
import com.mreigar.transportapp.presentation.mock.GetTransportsAroundLocationUseCaseInstrument.givenGetTransportsAroundLocationUseCase
import com.mreigar.transportapp.presentation.model.TransportResourceViewEntity
import com.mreigar.transportapp.presentation.presenter.transportresourcemap.MapVisibleRegion
import com.mreigar.transportapp.presentation.presenter.transportresourcemap.TransportResourceMapPresenter
import com.mreigar.transportapp.presentation.presenter.transportresourcemap.TransportResourceMapViewTranslator

object TransportResourceMapPresenterInstrument {

    fun givenTransportResourceMapPresenter(
        callbackResult: TransportResourceMapCallbackResult,
        repositoryStatus: RepositoryStatus = RepositoryStatus.SUCCESS,
        networkAvailable: Boolean = true
    ) = TransportResourceMapPresenter(
        givenTransportResourceMapViewTranslator(callbackResult, networkAvailable),
        givenGetTransportsAroundLocationUseCase(repositoryStatus),
        givenInvoker()
    )

    private fun givenTransportResourceMapViewTranslator(callbackResult: TransportResourceMapCallbackResult, networkAvailable: Boolean): TransportResourceMapViewTranslator =
        object : TransportResourceMapViewTranslator {
            override fun showTransportResources(transportResources: List<TransportResourceViewEntity>) {
                callbackResult.putMethodCall(TransportResourceMapViewMethod.SHOW_TRANSPORT_RESOURCES)
            }

            override fun showError() {
                callbackResult.putMethodCall(TransportResourceMapViewMethod.SHOW_ERROR)
            }

            override fun getMapVisibleRegion(): MapVisibleRegion {
                callbackResult.putMethodCall(TransportResourceMapViewMethod.GET_MAP_VISIBLE_REGION)
                return MapVisibleRegion(38.74, -9.15, 38.75, -9.15)
            }

            override fun isNetworkAvailable(): Boolean {
                callbackResult.putMethodCall(TransportResourceMapViewMethod.IS_NETWORK_AVAILABLE)
                return networkAvailable
            }

            override fun showNoInternetAvailable() {
                callbackResult.putMethodCall(TransportResourceMapViewMethod.SHOW_NO_INTERNET_AVAILABLE)
            }

            override fun hideNoInternetAvailable() {
                callbackResult.putMethodCall(TransportResourceMapViewMethod.HIDE_NO_INTERNET_AVAILABLE)
            }
        }
}

class TransportResourceMapCallbackResult : BaseCallbackResult<TransportResourceMapViewMethod>()

enum class TransportResourceMapViewMethod {
    SHOW_TRANSPORT_RESOURCES,
    SHOW_ERROR,
    GET_MAP_VISIBLE_REGION,
    IS_NETWORK_AVAILABLE,
    SHOW_NO_INTERNET_AVAILABLE,
    HIDE_NO_INTERNET_AVAILABLE
}