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
    fun `given presenter, when map region changes, map is updated based on new coordinates`() {
        presenter = givenTransportResourceMapPresenter(callbackResult, RepositoryStatus.SUCCESS)

        presenter.onMapRegionChanged()

        assertThat(callbackResult.isMethodFired(TransportResourceMapViewMethod.GET_MAP_VISIBLE_REGION)).isTrue()
    }

    @Test
    fun `given presenter, when data is retrieved, it is shown`() {
        presenter = givenTransportResourceMapPresenter(callbackResult, RepositoryStatus.SUCCESS)

        presenter.onMapRegionChanged()

        assertThat(callbackResult.isMethodFired(TransportResourceMapViewMethod.SHOW_TRANSPORT_RESOURCES)).isTrue()
    }

    @Test
    fun `given presenter, when data is not retrieved, error is shown`() {
        presenter = givenTransportResourceMapPresenter(callbackResult, RepositoryStatus.ERROR)

        presenter.onMapRegionChanged()

        assertThat(callbackResult.isMethodFired(TransportResourceMapViewMethod.SHOW_ERROR)).isTrue()
    }

    @Test
    fun `given presenter, when no internet connection available, banner is shown`() {
        presenter = givenTransportResourceMapPresenter(callbackResult, networkAvailable = false)

        presenter.onMapRegionChanged()

        assertThat(callbackResult.isMethodFired(TransportResourceMapViewMethod.IS_NETWORK_AVAILABLE)).isTrue()
        assertThat(callbackResult.isMethodFired(TransportResourceMapViewMethod.SHOW_NO_INTERNET_AVAILABLE)).isTrue()
    }

    @Test
    fun `given presenter, when internet connection available, banner is hidden`() {
        presenter = givenTransportResourceMapPresenter(callbackResult, networkAvailable = true)

        presenter.onMapRegionChanged()

        assertThat(callbackResult.isMethodFired(TransportResourceMapViewMethod.IS_NETWORK_AVAILABLE)).isTrue()
        assertThat(callbackResult.isMethodFired(TransportResourceMapViewMethod.HIDE_NO_INTERNET_AVAILABLE)).isTrue()
    }
}