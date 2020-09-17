package com.mreigar.transportapp.presentation.mock

import com.mreigar.transportapp.domain.mock.DomainInstrument
import com.mreigar.transportapp.domain.mock.GetTransportsAroundLocationUseCaseInstrument.givenGetTransportsAroundLocationUseCase
import com.mreigar.transportapp.domain.mock.RepositoryStatus
import com.mreigar.transportapp.presentation.BaseCallbackResult
import com.mreigar.transportapp.presentation.model.TransportResourceViewEntity
import com.mreigar.transportapp.presentation.presenter.transportresourcemap.TransportResourceMapPresenter
import com.mreigar.transportapp.presentation.presenter.transportresourcemap.TransportResourceMapViewTranslator

object TransportResourceMapPresenterInstrument {

    fun givenTransportResourceMapPresenter(
        callbackResult: TransportResourceMapCallbackResult,
        repositoryStatus: RepositoryStatus = RepositoryStatus.SUCCESS
    ) = TransportResourceMapPresenter(
        givenTransportResourceMapViewTranslator(callbackResult),
        givenGetTransportsAroundLocationUseCase(repositoryStatus),
        DomainInstrument.givenInvoker()
    )

    private fun givenTransportResourceMapViewTranslator(callbackResult: TransportResourceMapCallbackResult): TransportResourceMapViewTranslator =
        object : TransportResourceMapViewTranslator {
            override fun showTransportResources(transportResources: List<TransportResourceViewEntity>) {
                callbackResult.putMethodCall(TransportResourceMapViewMethod.SHOW_TRANSPORT_RESOURCES)
            }

            override fun showError() {
                callbackResult.putMethodCall(TransportResourceMapViewMethod.SHOW_ERROR)
            }
        }
}

class TransportResourceMapCallbackResult : BaseCallbackResult<TransportResourceMapViewMethod>()

enum class TransportResourceMapViewMethod {
    SHOW_TRANSPORT_RESOURCES,
    SHOW_ERROR,
}