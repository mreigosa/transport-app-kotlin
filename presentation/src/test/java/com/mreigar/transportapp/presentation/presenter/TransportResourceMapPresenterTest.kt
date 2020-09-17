package com.mreigar.transportapp.presentation.presenter

import com.mreigar.transportapp.domain.mock.RepositoryStatus
import com.mreigar.transportapp.presentation.mock.TransportResourceMapCallbackResult
import com.mreigar.transportapp.presentation.mock.TransportResourceMapPresenterInstrument.givenTransportResourceMapPresenter
import com.mreigar.transportapp.presentation.mock.TransportResourceMapViewMethod
import com.mreigar.transportapp.presentation.presenter.transportresourcemap.TransportResourceMapPresenter
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.koin.test.AutoCloseKoinTest

class TransportResourceMapPresenterTest : AutoCloseKoinTest() {

    private var callbackResult: TransportResourceMapCallbackResult = TransportResourceMapCallbackResult()

    private lateinit var presenter: TransportResourceMapPresenter

    @Test
    fun `given presenter, when data is retrieved, it is shown`() {
        presenter = givenTransportResourceMapPresenter(callbackResult, RepositoryStatus.SUCCESS)

        presenter.onReady()

        assertThat(callbackResult.isMethodFired(TransportResourceMapViewMethod.SHOW_TRANSPORT_RESOURCES)).isTrue()
    }

    @Test
    fun `given presenter, when data is not retrieved, error is shown`() {
        presenter = givenTransportResourceMapPresenter(callbackResult, RepositoryStatus.ERROR)

        presenter.onReady()

        assertThat(callbackResult.isMethodFired(TransportResourceMapViewMethod.SHOW_ERROR)).isTrue()
    }
}